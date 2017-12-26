package zikzakjack.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import zikzakjack.domain.Country;
import zikzakjack.domain.Location;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationRepositoryTests {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Test
	@Transactional
	public void testCRUD() {

		// Insert a New Location
		Country country = countryRepository.findByCountryIdEquals("US");
		Location location = new Location("433 Hidden Valley Drive", "08820", "Edison", "New Jersey", country);
		locationRepository.save(location);
		List<Location> locations = locationRepository.findByAddressCityEquals("Edison");
		Long locationId = locations.get(0).getLocationId();
		assertThat(locations).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(locations.get(0)).isEqualToComparingFieldByFieldRecursively(location);
		System.out.println("Insert: " + locations.get(0));

		// Update existing Location
		location.getAddress().setPostalCode("08830");
//		location.setPostalCode("08830");
		locationRepository.save(location);
		Location actualLocation = locationRepository.findByLocationIdEquals(locationId);
		assertThat(actualLocation).isNotNull().isEqualToComparingFieldByFieldRecursively(location);
		System.out.println("Update: " + actualLocation);

		// Delete existing Location
		locationRepository.delete(location);
		actualLocation = locationRepository.findByLocationIdEquals(locationId);
		assertThat(actualLocation).isNull();
	}

	@Test
	public void testFindByLocationIdEquals() {
		Country country = countryRepository.findByCountryIdEquals("IT");
		Location location = locationRepository.findByLocationIdEquals(1000L);
		Location expectedLocation = new Location(1000L, "1297 Via Cola di Rie", "00989", "Roma", null, country);
		assertThat(location).isNotNull().isEqualTo(expectedLocation);
		assertThat(location).isNotNull().isEqualToComparingFieldByFieldRecursively(expectedLocation);
	}

	@Test
	public void testFindByLocationIdLessThan() {
		Country country = countryRepository.findByCountryIdEquals("IT");
		List<Location> locations = locationRepository.findByLocationIdLessThan(1100L);
		Location expectedLocation = new Location(1000L, "1297 Via Cola di Rie", "00989", "Roma", null, country);
		assertThat(locations).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(locations.get(0)).isNotNull().isEqualTo(expectedLocation);
		assertThat(locations.get(0)).isNotNull().isEqualToComparingFieldByFieldRecursively(expectedLocation);
	}

	@Test
	public void testFindByLocationIdGreaterThan() {
		List<Location> locations = locationRepository.findByLocationIdGreaterThan(3000L);
		assertThat(locations).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByLocationIdLessThanEqual() {
		List<Location> locations = locationRepository.findByLocationIdLessThanEqual(1100L);
		assertThat(locations).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByLocationIdGreaterThanEqual() {
		List<Location> locations = locationRepository.findByLocationIdGreaterThanEqual(3000L);
		assertThat(locations).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByLocationIdBetween() {
		List<Location> locations = locationRepository.findByLocationIdBetween(1000L, 1500L);
		assertThat(locations).isNotEmpty().doesNotContainNull().size().isEqualTo(6);
	}

	@Test
	public void testFindByAddressStreetAddressIsNull() {
		List<Location> locations = locationRepository.findByAddressStreetAddressIsNull();
		assertThat(locations).isEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressIsNotNull() {
		List<Location> locations = locationRepository.findByAddressStreetAddressIsNotNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressStartingWith() {
		List<Location> locations = locationRepository.findByAddressStreetAddressStartingWith("Mag");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressEndingWith() {
		List<Location> locations = locationRepository.findByAddressStreetAddressEndingWith("Park");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressContaining() {
		List<Location> locations = locationRepository.findByAddressStreetAddressContaining("Centre");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressLike() {
		List<Location> locations = locationRepository.findByAddressStreetAddressLike("%Clementi%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressAddressStreetAddressLikeOrderByAddressStreetAddress() {
		List<Location> locations = locationRepository.findByAddressStreetAddressLikeOrderByAddressStreetAddress("1%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindAddressStreetAddressLikeOrderByAddressStreetAddressDesc() {
		List<Location> locations = locationRepository.findByAddressStreetAddressLikeOrderByAddressStreetAddressDesc("1%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressNotLike() {
		List<Location> locations = locationRepository.findByAddressStreetAddressNotLike("%Zebra%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStreetAddressEquals() {
		List<Location> locations = locationRepository.findByAddressStreetAddressEquals("12-98 Victoria Street");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeIsNull() {
		List<Location> locations = locationRepository.findByAddressPostalCodeIsNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeIsNotNull() {
		List<Location> locations = locationRepository.findByAddressPostalCodeIsNotNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeStartingWith() {
		List<Location> locations = locationRepository.findByAddressPostalCodeStartingWith("OX9");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeEndingWith() {
		List<Location> locations = locationRepository.findByAddressPostalCodeEndingWith("2L7");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeContaining() {
		List<Location> locations = locationRepository.findByAddressPostalCodeContaining("9Z");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeLike() {
		List<Location> locations = locationRepository.findByAddressPostalCodeLike("2901");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeLikeOrderByAddressPostalCode() {
		List<Location> locations = locationRepository.findByAddressPostalCodeLikeOrderByAddressPostalCode("1%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeLikeOrderByAddressPostalCodeDesc() {
		List<Location> locations = locationRepository.findByAddressPostalCodeLikeOrderByAddressPostalCodeDesc("1%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeNotLike() {
		List<Location> locations = locationRepository.findByAddressPostalCodeNotLike("1%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressPostalCodeEquals() {
		List<Location> locations = locationRepository.findByAddressPostalCodeEquals("3095");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityIsNull() {
		List<Location> locations = locationRepository.findByAddressCityIsNull();
		assertThat(locations).isEmpty();
	}

	@Test
	public void testFindByAddressCityIsNotNull() {
		List<Location> locations = locationRepository.findByAddressCityIsNotNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityStartingWith() {
		List<Location> locations = locationRepository.findByAddressCityStartingWith("Sin");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityEndingWith() {
		List<Location> locations = locationRepository.findByAddressCityEndingWith("ford");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityContaining() {
		List<Location> locations = locationRepository.findByAddressCityContaining("ok");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityLike() {
		List<Location> locations = locationRepository.findByAddressCityLike("South%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityLikeOrderByAddressCity() {
		List<Location> locations = locationRepository.findByAddressCityLikeOrderByAddressCity("South%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityLikeOrderByAddressCityDesc() {
		List<Location> locations = locationRepository.findByAddressCityLikeOrderByAddressCityDesc("South%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityNotLike() {
		List<Location> locations = locationRepository.findByAddressCityNotLike("South%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressCityEquals() {
		List<Location> locations = locationRepository.findByAddressCityEquals("Toronto");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceIsNull() {
		List<Location> locations = locationRepository.findByAddressStateProvinceIsNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceIsNotNull() {
		List<Location> locations = locationRepository.findByAddressStateProvinceIsNotNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceStartingWith() {
		List<Location> locations = locationRepository.findByAddressStateProvinceStartingWith("B");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceEndingWith() {
		List<Location> locations = locationRepository.findByAddressStateProvinceEndingWith("a");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceContaining() {
		List<Location> locations = locationRepository.findByAddressStateProvinceContaining("o");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceLike() {
		List<Location> locations = locationRepository.findByAddressStateProvinceLike("New%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceLikeOrderByAddressStateProvince() {
		List<Location> locations = locationRepository.findByAddressStateProvinceLikeOrderByAddressStateProvince("New%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceLikeOrderByAddressStateProvinceDesc() {
		List<Location> locations = locationRepository.findByAddressStateProvinceLikeOrderByAddressStateProvinceDesc("New%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceNotLike() {
		List<Location> locations = locationRepository.findByAddressStateProvinceNotLike("New%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByAddressStateProvinceEquals() {
		List<Location> locations = locationRepository.findByAddressStateProvinceEquals("Yukon");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdIsNull() {
		List<Location> locations = locationRepository.findByCountryCountryIdIsNull();
		assertThat(locations).isEmpty();
	}

	@Test
	public void testFindByCountryCountryIdIsNotNull() {
		List<Location> locations = locationRepository.findByCountryCountryIdIsNotNull();
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdStartingWith() {
		List<Location> locations = locationRepository.findByCountryCountryIdStartingWith("C");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdEndingWith() {
		List<Location> locations = locationRepository.findByCountryCountryIdEndingWith("K");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdContaining() {
		List<Location> locations = locationRepository.findByCountryCountryIdContaining("J");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdLike() {
		List<Location> locations = locationRepository.findByCountryCountryIdLike("I%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdLikeOrderByCountryCountryId() {
		List<Location> locations = locationRepository.findByCountryCountryIdLikeOrderByCountryCountryId("I%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdLikeOrderByCountryCountryIdDesc() {
		List<Location> locations = locationRepository.findByCountryCountryIdLikeOrderByCountryCountryIdDesc("I%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdNotLike() {
		List<Location> locations = locationRepository.findByCountryCountryIdNotLike("I%");
		assertThat(locations).isNotEmpty();
	}

	@Test
	public void testFindByCountryCountryIdEquals() {
		List<Location> locations = locationRepository.findByCountryCountryIdEquals("US");
		assertThat(locations).isNotEmpty();
	}

}
