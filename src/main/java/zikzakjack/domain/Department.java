package zikzakjack.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DEPARTMENTS")
public class Department implements Serializable, Comparable<Department> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_seq")
	@SequenceGenerator(name = "departments_seq", sequenceName = "departments_seq", allocationSize = 1)
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@OneToOne
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

	public Department() {
	}

	public Department(Long departmentId, String departmentName, Employee manager, Location location) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.manager = manager;
		this.location = location;
	}

	public Department(String departmentName, Employee manager, Location location) {
		super();
		this.departmentName = departmentName;
		this.manager = manager;
		this.location = location;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", manager="
				+ manager + ", location=" + location + "]";
	}

	@Override
	public int compareTo(Department o) {
		return departmentId.compareTo(departmentId);
	}

}
