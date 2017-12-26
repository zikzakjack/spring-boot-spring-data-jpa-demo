package zikzakjack;

import static java.util.Comparator.comparing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import zikzakjack.domain.Country;
import zikzakjack.domain.Location;
import zikzakjack.domain.Region;
import zikzakjack.repository.CountryRepository;
import zikzakjack.repository.LocationRepository;
import zikzakjack.repository.RegionRepository;

@SpringBootApplication
public class SpringBootSpringDataJpaApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootSpringDataJpaApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootSpringDataJpaApplication.class, args);

		RegionRepository regionRepository = (RegionRepository) context.getBean("regionRepository");
		logger.info("\n\nRegions Count : " + regionRepository.count() + "\n\n");
		logger.info("\n\nRegion #1 : " + regionRepository.findByRegionIdEquals(1L) + "\n\n");
		// regionRepository.findAll().stream().forEach(System.out::println);
		// regionRepository.findAll().stream().sorted((r1, r2) -> (int) (r1.getRegionId() - r2.getRegionId())).forEach(System.out::println);
		regionRepository.findAll().stream().sorted(comparing(Region::getRegionId)).map((region)-> (region.toString())).forEach(logger::info);

		CountryRepository countryRepository = (CountryRepository) context.getBean("countryRepository");
		logger.info("\n\nCountries Count : " + countryRepository.count() + "\n\n");
		logger.info("\n\nCountry #IT : " + countryRepository.findByCountryIdEquals("IT") + "\n\n");
		// countryRepository.findAll().stream().forEach(System.out::println);
		// countryRepository.findAll().stream().sorted((c1, c2) -> c1.getCountryId().compareTo(c2.getCountryId())).forEach(System.out::println);
		countryRepository.findAll().stream().sorted(comparing(Country::getRegion).thenComparing(Country::getCountryName)).map((country)-> (country.toString())).forEach(logger::info);

		LocationRepository locationRepository = (LocationRepository) context.getBean("locationRepository");
		logger.info("\n\nLocations Count : " + locationRepository.count() + "\n\n");
		logger.info("\n\nLocation #1000 : " + locationRepository.findByLocationIdEquals(1000L) + "\n\n");
		locationRepository.findAll().stream().sorted(comparing(Location::getLocationId)).map((location)-> (location.toString())).forEach(logger::info);
		logger.info("\n\nStreetAddress is NULL : " + locationRepository.findByAddressStreetAddressIsNull().size() + "\n\n");
		logger.info("\n\nStreetAddress is NOT NULL : " + locationRepository.findByAddressStreetAddressIsNotNull().size() + "\n\n");
		logger.info("\n\nPostalCode is NULL : " + locationRepository.findByAddressPostalCodeIsNull().size() + "\n\n");
		logger.info("\n\nPostalCode is NOT NULL : " + locationRepository.findByAddressPostalCodeIsNotNull().size() + "\n\n");
		logger.info("\n\nStateProvince is NULL : " + locationRepository.findByAddressStateProvinceIsNull().size() + "\n\n");
		logger.info("\n\nStateProvince is NOT NULL : " + locationRepository.findByAddressStateProvinceIsNotNull().size() + "\n\n");
		logger.info("\n\nLocations in US : " + locationRepository.findByCountryCountryIdEquals("US").size() + "\n\n");

	}
}
