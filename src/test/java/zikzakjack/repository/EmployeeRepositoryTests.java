package zikzakjack.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import zikzakjack.domain.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Transactional
	public void testCRUD() {
		// Insert a New Employee
		Employee employee = new Employee("ZikZak", "Jack", "zikzakjack", "123456", new Date(System.currentTimeMillis()), "FI_ACCOUNT", 20000.00, 0.1F, 103L, 50L);
		employeeRepository.save(employee);
		List<Employee> employees = employeeRepository.findByFirstNameEquals("ZikZak");
		Long employeeId = employees.get(0).getEmployeeId();
		assertThat(employees).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(employees.get(0)).isEqualToComparingFieldByFieldRecursively(employee);
		System.out.println(employees.get(0));

		// Update existing Employee
		employee.setFirstName("TeenaMeena");
		employeeRepository.save(employee);
		Employee actualEmployee = employeeRepository.findByEmployeeIdEquals(employeeId);
		assertThat(actualEmployee).isNotNull().isEqualToComparingFieldByFieldRecursively(employee);

		// Delete existing Employee
		employeeRepository.delete(employee);
		actualEmployee = employeeRepository.findByEmployeeIdEquals(employeeId);
		assertThat(actualEmployee).isNull();
	}

	@Test
	public void testFindAll() {
		List<Employee> employees = employeeRepository.findAll();
		assertThat(employees).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void testFindByEmployeeIdEquals() {
		Employee employee = employeeRepository.findByEmployeeIdEquals(108L);
		assertThat(employee).isNotNull();
	}

	@Test
	public void testFindByFirstNameEquals() {
		List<Employee> employees = employeeRepository.findByFirstNameEquals("David");
		assertThat(employees).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void testFindByLastNameEquals() {
		List<Employee> employees = employeeRepository.findByLastNameEquals("Cambrault");
		assertThat(employees).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void testFindByEmailEquals() {
		Employee employee = employeeRepository.findByEmailEquals("AKHOO");
		assertThat(employee).isNotNull();
	}

	@Test
	public void testFindByHireDateEquals() {
		List<Employee> employees = employeeRepository.findByHireDateEquals(Date.valueOf("1994-06-07"));
		assertThat(employees).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void testFindByJobIdEquals() {
		List<Employee> employees = employeeRepository.findByJobIdEquals("IT_PROG");
		assertThat(employees).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void testFindByManagerIdEquals() {
		List<Employee> employees = employeeRepository.findByManagerIdEquals(122L);
		assertThat(employees).isNotEmpty().doesNotContainNull();
	}

	@Test
	public void testFindByDepartmentIdEquals() {
		List<Employee> employees = employeeRepository.findByDepartmentIdEquals(50L);
		assertThat(employees).isNotEmpty().doesNotContainNull();

	}

}
