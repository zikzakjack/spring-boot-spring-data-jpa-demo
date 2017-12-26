package zikzakjack.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "REGIONS")
public class Region implements Serializable, Comparable<Region> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REGION_ID")
	private Long regionId;

	@Column(name = "REGION_NAME")
	private String regionName;

	@OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
	private Set<Country> countries = new HashSet<Country>();

	public Region() {
	}

	public Region(Long regionId, String regionName) {
		this.regionId = regionId;
		this.regionName = regionName;
	}

	public Region(String regionName) {
		this.regionName = regionName;
	}

	public void addCountry(Country country) {
		countries.add(country);
		country.setRegion(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		if (regionName == null) {
			if (other.regionName != null)
				return false;
		} else if (!regionName.equals(other.regionName))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
		result = prime * result + ((regionName == null) ? 0 : regionName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName + "]";
	}
	
	@Override
	public int compareTo(Region o) {
		return regionId.compareTo(o.regionId);
	}

}
