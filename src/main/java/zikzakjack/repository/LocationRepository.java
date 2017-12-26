package zikzakjack.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zikzakjack.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	public Location findByLocationIdEquals(Long locationId);

	public List<Location> findByLocationIdLessThan(Long locationId);

	public List<Location> findByLocationIdGreaterThan(Long locationId);

	public List<Location> findByLocationIdLessThanEqual(Long locationId);

	public List<Location> findByLocationIdGreaterThanEqual(Long locationId);

	public List<Location> findByLocationIdBetween(Long min, Long max);

	public List<Location> findByLocationIdInOrderByLocationId(Collection<Long> relocations);

	public List<Location> findByLocationIdInOrderByLocationIdDesc(Collection<Long> locationIds);

	public List<Location> findByAddressStreetAddressIsNull();

	public List<Location> findByAddressStreetAddressIsNotNull();

	public List<Location> findByAddressStreetAddressStartingWith(String streetAddress);

	public List<Location> findByAddressStreetAddressEndingWith(String streetAddress);

	public List<Location> findByAddressStreetAddressContaining(String streetAddress);

	public List<Location> findByAddressStreetAddressLike(String streetAddress);

	public List<Location> findByAddressStreetAddressLikeOrderByAddressStreetAddress(String streetAddress);

	public List<Location> findByAddressStreetAddressLikeOrderByAddressStreetAddressDesc(String streetAddress);

	public List<Location> findByAddressStreetAddressNotLike(String streetAddress);

	public List<Location> findByAddressStreetAddressEquals(String streetAddress);

	public List<Location> findByAddressPostalCodeIsNull();

	public List<Location> findByAddressPostalCodeIsNotNull();

	public List<Location> findByAddressPostalCodeStartingWith(String postalCode);

	public List<Location> findByAddressPostalCodeEndingWith(String postalCode);

	public List<Location> findByAddressPostalCodeContaining(String postalCode);

	public List<Location> findByAddressPostalCodeLike(String postalCode);

	public List<Location> findByAddressPostalCodeLikeOrderByAddressPostalCode(String postalCode);

	public List<Location> findByAddressPostalCodeLikeOrderByAddressPostalCodeDesc(String postalCode);

	public List<Location> findByAddressPostalCodeNotLike(String postalCode);

	public List<Location> findByAddressPostalCodeEquals(String postalCode);

	public List<Location> findByAddressCityIsNull();

	public List<Location> findByAddressCityIsNotNull();

	public List<Location> findByAddressCityStartingWith(String city);

	public List<Location> findByAddressCityEndingWith(String city);

	public List<Location> findByAddressCityContaining(String city);

	public List<Location> findByAddressCityLike(String city);

	public List<Location> findByAddressCityLikeOrderByAddressCity(String city);

	public List<Location> findByAddressCityLikeOrderByAddressCityDesc(String city);

	public List<Location> findByAddressCityNotLike(String city);

	public List<Location> findByAddressCityEquals(String city);

	public List<Location> findByAddressStateProvinceIsNull();

	public List<Location> findByAddressStateProvinceIsNotNull();

	public List<Location> findByAddressStateProvinceStartingWith(String stateProvince);

	public List<Location> findByAddressStateProvinceEndingWith(String stateProvince);

	public List<Location> findByAddressStateProvinceContaining(String stateProvince);

	public List<Location> findByAddressStateProvinceLike(String stateProvince);

	public List<Location> findByAddressStateProvinceLikeOrderByAddressStateProvince(String stateProvince);

	public List<Location> findByAddressStateProvinceLikeOrderByAddressStateProvinceDesc(String stateProvince);

	public List<Location> findByAddressStateProvinceNotLike(String stateProvince);

	public List<Location> findByAddressStateProvinceEquals(String stateProvince);

	public List<Location> findByCountryCountryIdIsNull();

	public List<Location> findByCountryCountryIdIsNotNull();

	public List<Location> findByCountryCountryIdStartingWith(String countryId);

	public List<Location> findByCountryCountryIdEndingWith(String countryId);

	public List<Location> findByCountryCountryIdContaining(String countryId);

	public List<Location> findByCountryCountryIdLike(String countryId);

	public List<Location> findByCountryCountryIdLikeOrderByCountryCountryId(String countryId);

	public List<Location> findByCountryCountryIdLikeOrderByCountryCountryIdDesc(String countryId);

	public List<Location> findByCountryCountryIdNotLike(String countryId);

	public List<Location> findByCountryCountryIdEquals(String countryId);

	public void deleteByLocationIdEquals(Long locationId);

}
