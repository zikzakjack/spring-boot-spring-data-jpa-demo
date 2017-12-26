package zikzakjack.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import zikzakjack.domain.Department;
import zikzakjack.domain.Employee;
import zikzakjack.domain.Location;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRepositoryTests {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Test
	@Transactional
	public void testCRUD() {

		// Insert a New Department
		Location location = locationRepository.findByLocationIdEquals(1700L);
		Department department = new Department("RiskTeam", null, location);
		departmentRepository.save(department);
		Department expectedDepartment = departmentRepository.findByDepartmentNameEquals("RiskTeam");
		Long departmentId = expectedDepartment.getDepartmentId();
		assertThat(expectedDepartment).isNotNull();
		assertThat(expectedDepartment).isEqualToComparingFieldByFieldRecursively(department);

		// Update existing Department
		department.setDepartmentName("RiskIT");
		departmentRepository.save(department);
		Department actualDepartment = departmentRepository.findByDepartmentIdEquals(departmentId);
		assertThat(actualDepartment).isNotNull().isEqualToComparingFieldByFieldRecursively(department);

		// Delete existing Department
		departmentRepository.delete(department);
		actualDepartment = departmentRepository.findByDepartmentIdEquals(departmentId);
		assertThat(actualDepartment).isNull();
	}

	@Test
	public void testFindAll() {
		List<Department> departments = departmentRepository.findAll();
		assertThat(departments).isNotEmpty().doesNotContainNull().size().isEqualTo(27);
	}

	@Test
	public void testFindByDepartmentIdEquals() {
		Department department = departmentRepository.findByDepartmentIdEquals(10L);
		Employee manager = employeeRepository.findByEmployeeIdEquals(200L);
		Location location = locationRepository.findByLocationIdEquals(1700L);
		Department expectedDepartment = new Department(10L, "Administration", manager, location);
		assertThat(department).isNotNull().isEqualTo(expectedDepartment);
		assertThat(department).isNotNull().isEqualToComparingFieldByFieldRecursively(expectedDepartment);
	}

	@Test
	public void testFindByDepartmentNameEquals() {
		Department department = departmentRepository.findByDepartmentNameEquals("Administration");
		Employee manager = employeeRepository.findByEmployeeIdEquals(200L);
		Location location = locationRepository.findByLocationIdEquals(1700L);
		Department expectedDepartment = new Department(10L, "Administration", manager, location);
		assertThat(department).isNotNull().isEqualTo(expectedDepartment);
		assertThat(department).isNotNull().isEqualToComparingFieldByFieldRecursively(expectedDepartment);
	}

	@Test
	public void testFindByManagerEmployeeIdEquals() {
		List<Department> departments = departmentRepository.findByManagerEmployeeIdEquals(200L);
		Department department = departments.get(0);
		Employee manager = employeeRepository.findByEmployeeIdEquals(200L);
		Location location = locationRepository.findByLocationIdEquals(1700L);
		Department expectedDepartment = new Department(10L, "Administration", manager, location);
		assertThat(departments).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(department).isNotNull().isEqualTo(expectedDepartment);
		assertThat(department).isNotNull().isEqualToComparingFieldByFieldRecursively(expectedDepartment);
	}

	@Test
	public void findByLocationLocationIdEquals() {
		List<Department> departments = departmentRepository.findByLocationLocationIdEquals(1700L);
		assertThat(departments).isNotEmpty().doesNotContainNull();
	}

}
