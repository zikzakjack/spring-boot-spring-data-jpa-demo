package zikzakjack.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zikzakjack.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Country findByCountryIdEquals(String countryId);

	public List<Country> findByCountryIdInOrderByCountryId(Collection<String> countryIds);

	public List<Country> findByCountryIdInOrderByCountryIdDesc(Collection<String> countryIds);

	public List<Country> findByCountryNameStartingWith(String countryName);

	public List<Country> findByCountryNameEndingWith(String countryName);

	public List<Country> findByCountryNameContaining(String countryName);

	public List<Country> findByCountryNameLike(String countryName);

	public List<Country> findByCountryNameLikeOrderByCountryName(String countryName);

	public List<Country> findByCountryNameLikeOrderByCountryNameDesc(String countryName);

	public List<Country> findByCountryNameNotLike(String countryName);

	public void deleteByCountryIdEquals(String countryId);
}
