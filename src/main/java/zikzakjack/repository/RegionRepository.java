package zikzakjack.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zikzakjack.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

	public Region findByRegionIdEquals(Long regionId);

	public List<Region> findByRegionIdLessThan(Long regionId);

	public List<Region> findByRegionIdGreaterThan(Long regionId);

	public List<Region> findByRegionIdLessThanEqual(Long regionId);

	public List<Region> findByRegionIdGreaterThanEqual(Long regionId);

	public List<Region> findByRegionIdBetween(Long min, Long max);

	public List<Region> findByRegionIdInOrderByRegionId(Collection<Long> regionIds);

	public List<Region> findByRegionIdInOrderByRegionIdDesc(Collection<Long> regionIds);

	public List<Region> findByRegionNameStartingWith(String regionName);

	public List<Region> findByRegionNameEndingWith(String regionName);

	public List<Region> findByRegionNameContaining(String regionName);

	public List<Region> findByRegionNameLike(String regionName);

	public List<Region> findByRegionNameLikeOrderByRegionName(String regionName);

	public List<Region> findByRegionNameLikeOrderByRegionNameDesc(String regionName);

	public List<Region> findByRegionNameNotLike(String regionName);

	public void deleteByRegionIdEquals(Long regionId);
}
