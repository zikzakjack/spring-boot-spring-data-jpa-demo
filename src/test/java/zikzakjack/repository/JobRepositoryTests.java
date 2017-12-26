package zikzakjack.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import zikzakjack.domain.Job;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobRepositoryTests {

	@Autowired
	private JobRepository jobRepository;

	@Test
	@Transactional
	public void testCRUD() {
		// Insert a New Job
		Job job = new Job("BD_ENG", "Data Engineer", 6000L, 10000L);
		jobRepository.save(job);
		Job actualJob = jobRepository.findByJobIdEquals("BD_ENG");
		String jobId = actualJob.getJobId();
		assertThat(actualJob).isNotNull().isEqualToComparingFieldByFieldRecursively(job);
		System.out.println(actualJob);

		// Update existing Job
		job.setJobTitle("BigData Engineer");
		jobRepository.save(job);
		actualJob = jobRepository.findByJobIdEquals(jobId);
		assertThat(actualJob).isNotNull().isEqualToComparingFieldByFieldRecursively(job);

		// Delete existing Job
		jobRepository.delete(job);
		actualJob = jobRepository.findByJobIdEquals(jobId);
		assertThat(actualJob).isNull();
	}

	@Test
	public void testFindAll() {
		List<Job> jobs = jobRepository.findAll();
		assertThat(jobs).isNotEmpty().doesNotContainNull().size().isEqualTo(19);
	}

	@Test
	public void testFindByJobIdEquals() {
		Job job = jobRepository.findByJobIdEquals("AC_ACCOUNT");
		Job expectedJob = new Job("AC_ACCOUNT", "Public Accountant", 4200L, 9000L);
		assertThat(job).isNotNull().isEqualToComparingFieldByFieldRecursively(expectedJob);
	}

	@Test
	public void findByJobTitleStartingWith() {
		List<Job> jobs = jobRepository.findByJobTitleStartingWith("A");
		assertThat(jobs).isNotEmpty().doesNotContainNull().size().isEqualTo(4);

	}

	@Test
	public void findByJobTitleEndingWith() {
		List<Job> jobs = jobRepository.findByJobTitleEndingWith("Representative");
		assertThat(jobs).isNotEmpty().doesNotContainNull().size().isEqualTo(4);
	}

	@Test
	public void findByJobTitleContaining() {
		List<Job> jobs = jobRepository.findByJobTitleContaining("Human");
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByJobTitleLike() {
		List<Job> jobs = jobRepository.findByJobTitleLike("Purchasing%");
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByJobTitleLikeOrderByJobTitle() {
		List<Job> jobs = jobRepository.findByJobTitleLike("Sales%");
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByJobTitleLikeOrderByJobTitleDesc() {
		List<Job> jobs = jobRepository.findByJobTitleLike("Sales%");
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByJobTitleNotLike() {
		List<Job> jobs = jobRepository.findByJobTitleLike("Sales%");
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMinimumSalaryLessThan() {
		List<Job> jobs = jobRepository.findByMinimumSalaryLessThan(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMinimumSalaryGreaterThan() {
		List<Job> jobs = jobRepository.findByMinimumSalaryGreaterThan(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMinimumSalaryLessThanEqual() {
		List<Job> jobs = jobRepository.findByMinimumSalaryLessThanEqual(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMinimumSalaryGreaterThanEqual() {
		List<Job> jobs = jobRepository.findByMinimumSalaryGreaterThanEqual(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMinimumSalaryBetween() {
		List<Job> jobs = jobRepository.findByMinimumSalaryBetween(5000L, 10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMaximumSalaryLessThan() {
		List<Job> jobs = jobRepository.findByMaximumSalaryLessThan(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMaximumSalaryGreaterThan() {
		List<Job> jobs = jobRepository.findByMaximumSalaryGreaterThan(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMaximumSalaryLessThanEqual() {
		List<Job> jobs = jobRepository.findByMaximumSalaryLessThanEqual(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMaximumSalaryGreaterThanEqual() {
		List<Job> jobs = jobRepository.findByMaximumSalaryGreaterThanEqual(10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void findByMaximumSalaryBetween() {
		List<Job> jobs = jobRepository.findByMaximumSalaryBetween(5000L, 10000L);
		assertThat(jobs).isNotEmpty().doesNotContainNull();
	}

}
