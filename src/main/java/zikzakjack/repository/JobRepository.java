package zikzakjack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zikzakjack.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

	public Job findByJobIdEquals(String jobId);

	public Job findByJobTitleEquals(String jobTitle);

	public List<Job> findByJobTitleStartingWith(String jobTitle);

	public List<Job> findByJobTitleEndingWith(String jobTitle);

	public List<Job> findByJobTitleContaining(String jobTitle);

	public List<Job> findByJobTitleLike(String jobTitle);

	public List<Job> findByJobTitleLikeOrderByJobTitle(String jobTitle);

	public List<Job> findByJobTitleLikeOrderByJobTitleDesc(String jobTitle);

	public List<Job> findByJobTitleNotLike(String jobTitle);

	public List<Job> findByMinimumSalaryLessThan(Long minimumSalary);

	public List<Job> findByMinimumSalaryGreaterThan(Long minimumSalary);

	public List<Job> findByMinimumSalaryLessThanEqual(Long minimumSalary);

	public List<Job> findByMinimumSalaryGreaterThanEqual(Long minimumSalary);

	public List<Job> findByMinimumSalaryBetween(Long min, Long max);

	public List<Job> findByMaximumSalaryLessThan(Long maximumSalary);

	public List<Job> findByMaximumSalaryGreaterThan(Long maximumSalary);

	public List<Job> findByMaximumSalaryLessThanEqual(Long maximumSalary);

	public List<Job> findByMaximumSalaryGreaterThanEqual(Long maximumSalary);

	public List<Job> findByMaximumSalaryBetween(Long min, Long max);

	public void deleteByJobIdEquals(String jobId);
}
