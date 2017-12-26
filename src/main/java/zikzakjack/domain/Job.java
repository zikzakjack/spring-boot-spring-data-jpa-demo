package zikzakjack.domain;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "JOBS")
public class Job implements Serializable, Comparable<Job> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "JOB_ID")
	private String jobId;

	@Column(name = "JOB_TITLE")
	private String jobTitle;

	@Column(name = "MIN_SALARY")
	private Long minimumSalary;

	@Column(name = "MAX_SALARY")
	private Long maximumSalary;

	public Job() {
	}

	public Job(String jobId, String jobTitle, Long minimumSalary, Long maximumSalary) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minimumSalary = minimumSalary;
		this.maximumSalary = maximumSalary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((maximumSalary == null) ? 0 : maximumSalary.hashCode());
		result = prime * result + ((minimumSalary == null) ? 0 : minimumSalary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (maximumSalary == null) {
			if (other.maximumSalary != null)
				return false;
		} else if (!maximumSalary.equals(other.maximumSalary))
			return false;
		if (minimumSalary == null) {
			if (other.minimumSalary != null)
				return false;
		} else if (!minimumSalary.equals(other.minimumSalary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", minimumSalary=" + minimumSalary + ", maximumSalary="
				+ maximumSalary + "]";
	}

	@Override
	public int compareTo(Job o) {
		return Comparator.comparing(Job::getJobId).thenComparing(Job::getJobTitle).compare(this, o);
	}

}
