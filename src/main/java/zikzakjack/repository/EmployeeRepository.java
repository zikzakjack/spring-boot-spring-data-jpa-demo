package zikzakjack.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zikzakjack.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByEmployeeIdEquals(Long employeeId);

	public List<Employee> findByFirstNameEquals(String firstName);

	public List<Employee> findByLastNameEquals(String lastName);

	public Employee findByEmailEquals(String email);

	public List<Employee> findByHireDateEquals(Date hireDate);

	public List<Employee> findByJobIdEquals(String jobId);

	public List<Employee> findByManagerIdEquals(Long managerId);

	public List<Employee> findByDepartmentIdEquals(Long departmentId);

	public void deleteByEmployeeIdEquals(Long employeeId);

}
