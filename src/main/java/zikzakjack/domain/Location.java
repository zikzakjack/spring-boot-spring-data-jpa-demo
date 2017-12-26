package zikzakjack.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LOCATIONS")
public class Location implements Serializable, Comparable<Location> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locations_seq")
	@SequenceGenerator(name = "locations_seq", sequenceName = "locations_seq", allocationSize = 1)
	@Column(name = "LOCATION_ID")
	private Long locationId;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "streetAddress", column = @Column(name = "STREET_ADDRESS")),
			@AttributeOverride(name = "postalCode", column = @Column(name = "POSTAL_CODE")),
			@AttributeOverride(name = "city", column = @Column(name = "CITY")),
			@AttributeOverride(name = "stateProvince", column = @Column(name = "STATE_PROVINCE")) })
	private Address address;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	public Location() {

	}

	public Location(Long locationId, String streetAddress, String postalCode, String city, String stateProvince,
			Country country) {
		this.locationId = locationId;
		this.address = new Address(streetAddress, postalCode, city, stateProvince);
		this.country= country;
	}

	public Location(String streetAddress, String postalCode, String city, String stateProvince, Country country) {
		this.address = new Address(streetAddress, postalCode, city, stateProvince);
		this.country= country;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", address=" + address + ", country=" + country + "]";
	}

	@Override
	public int compareTo(Location o) {
		return locationId.compareTo(o.locationId);
	}

}
