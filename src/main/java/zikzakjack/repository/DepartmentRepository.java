package zikzakjack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zikzakjack.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public Department findByDepartmentIdEquals(Long departmentId);

	public Department findByDepartmentNameEquals(String departmentName);

	public List<Department> findByManagerEmployeeIdEquals(Long managerId);

	public List<Department> findByLocationLocationIdEquals(Long locationId);

	public void deleteByDepartmentIdEquals(Long departmentId);

}
