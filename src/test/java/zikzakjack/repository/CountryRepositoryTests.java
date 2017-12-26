package zikzakjack.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import zikzakjack.domain.Country;
import zikzakjack.domain.Region;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryRepositoryTests {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Test
	@Transactional
	public void testCRUD() {

		// Insert a New Country
		Region region = regionRepository.findByRegionIdEquals(4L);
		Country country = new Country("ZZ", "ZebraLand", region);
		countryRepository.save(country);
		List<Country> countries = countryRepository.findByCountryNameLike("ZebraLand");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(countries.get(0)).isEqualToComparingFieldByFieldRecursively(country);

		// Update existing Country
		country.setCountryName("ZebraWorld");
		countryRepository.save(country);
		Country actualCountry = countryRepository.findByCountryIdEquals("ZZ");
		assertThat(actualCountry).isNotNull().isEqualToComparingFieldByFieldRecursively(country);

		// Delete existing Country
		countryRepository.delete(country);
		actualCountry = countryRepository.findByCountryIdEquals("ZZ");
		assertThat(actualCountry).isNull();
	}

	@Test
	public void testFindAll() {
		List<Country> countries = countryRepository.findAll();
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(25);
	}

	@Test
//	@Transactional
	public void testFindByCountryIdEquals() {
		Country country = countryRepository.findByCountryIdEquals("FR");
		Region region = regionRepository.findByRegionIdEquals(1L);
		Country expectedCountry = new Country("FR", "France", region);
		assertThat(country).isNotNull().isEqualTo(expectedCountry);
		assertThat(country.getRegion()).isNotNull().isEqualTo(region);
		assertThat(country.getRegion().getCountries()).isNotEmpty().doesNotContainNull().size().isEqualTo(8);
	}

	@Test
	public void testFindByCountryIdInOrderByCountryId() {
		List<Country> countries = countryRepository
				.findByCountryIdInOrderByCountryId(Arrays.asList("IT", "US", "JP", "ZZZZ"));
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByCountryIdInOrderByCountryIdDesc() {
		List<Country> countries = countryRepository
				.findByCountryIdInOrderByCountryIdDesc(Arrays.asList("IT", "US", "JP", "ZZZZ"));
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByCountryNameStartingWith() {
		List<Country> countries = countryRepository.findByCountryNameStartingWith("A");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByCountryNameEndingWith() {
		List<Country> countries = countryRepository.findByCountryNameEndingWith("g");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
	}

	@Test
	public void testFindByCountryNameContaining() {
		List<Country> countries = countryRepository.findByCountryNameContaining("gy");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
	}

	@Test
	public void testFindByCountryNameLike() {
		List<Country> countries = countryRepository.findByCountryNameLike("Bel%");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
	}

	@Test
	public void testFindByCountryNameLikeOrderByCountryName() {
		List<Country> countries = countryRepository.findByCountryNameLikeOrderByCountryName("A%");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByCountryNameLikeOrderByCountryNameDesc() {
		List<Country> countries = countryRepository.findByCountryNameLikeOrderByCountryNameDesc("A%");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByCountryNameNotLike() {
		List<Country> countries = countryRepository.findByCountryNameNotLike("%Amer%");
		assertThat(countries).isNotEmpty().doesNotContainNull().size().isEqualTo(24);
	}

}
