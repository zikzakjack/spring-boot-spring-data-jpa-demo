package zikzakjack.domain;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address implements Serializable, Comparable<Address> {

	private static final long serialVersionUID = 1L;

	private String streetAddress;

	private String postalCode;

	private String city;

	private String stateProvince;

	public Address() {

	}

	public Address(String streetAddress, String postalCode, String city, String stateProvince) {
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
		this.stateProvince = stateProvince;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (stateProvince == null) {
			if (other.stateProvince != null)
				return false;
		} else if (!stateProvince.equals(other.stateProvince))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((stateProvince == null) ? 0 : stateProvince.hashCode());
		result = prime * result + ((streetAddress == null) ? 0 : streetAddress.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + ", postalCode=" + postalCode + ", city=" + city
				+ ", stateProvince=" + stateProvince + "]";
	}

	@Override
	public int compareTo(Address o) {
		return Comparator.comparing(Address::getStateProvince).thenComparing(Address::getCity)
				.thenComparing(Address::getPostalCode).thenComparing(Address::getStreetAddress).compare(this, o);
	}

}
