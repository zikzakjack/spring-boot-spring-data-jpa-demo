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

import zikzakjack.domain.Region;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionRepositoryTests {

	@Autowired
	private RegionRepository regionRepository;

	@Test
	@Transactional
	public void testCRUD() {
		// Insert a New Region
		Region region = new Region("Antartica");
		regionRepository.save(region);
		List<Region> regions = regionRepository.findByRegionNameLike("Antartica");
		Long regionId = regions.get(0).getRegionId();
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(regions.get(0)).isEqualToComparingFieldByFieldRecursively(region);
		System.out.println(regions.get(0));

		// Update existing Region
		region.setRegionName("Artica");
		regionRepository.save(region);
		Region actualRegion = regionRepository.findByRegionIdEquals(regionId);
		assertThat(actualRegion).isNotNull().isEqualToComparingFieldByFieldRecursively(region);

		// Delete existing Region
		regionRepository.delete(region);
		actualRegion = regionRepository.findByRegionIdEquals(regionId);
		assertThat(actualRegion).isNull();
	}

	@Test
	public void testFindAll() {
		List<Region> regions = regionRepository.findAll();
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(4);
	}

	@Test
//	@Transactional
	public void testFindByRegionIdEquals() {
		Region region = regionRepository.findByRegionIdEquals(1L);
		Region expectedRegion = new Region(1L, "Europe");
		assertThat(region).isNotNull().isEqualTo(expectedRegion);
		assertThat(region.getCountries()).isNotEmpty().doesNotContainNull().size().isEqualTo(8);
	}

	@Test
	public void testFindByRegionIdLessThan() {
		List<Region> regions = regionRepository.findByRegionIdLessThan(2L);
		Region expectedRegion = new Region(1L, "Europe");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
		assertThat(regions.get(0)).isEqualTo(expectedRegion);
	}

	@Test
	public void testFindByRegionIdGreaterThan() {
		List<Region> regions = regionRepository.findByRegionIdGreaterThan(2L);
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionIdLessThanEqual() {
		List<Region> regions = regionRepository.findByRegionIdLessThanEqual(3L);
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByRegionIdGreaterThanEqual() {
		List<Region> regions = regionRepository.findByRegionIdGreaterThanEqual(3L);
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionIdBetween() {
		List<Region> regions = regionRepository.findByRegionIdBetween(2L, 4L);
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByRegionIdInOrderByRegionId() {
		List<Region> regions = regionRepository.findByRegionIdInOrderByRegionId(Arrays.asList(1L, 2L, 3L, 5L));
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByRegionIdInOrderByRegionIdDesc() {
		List<Region> regions = regionRepository.findByRegionIdInOrderByRegionIdDesc(Arrays.asList(1L, 2L, 3L, 5L));
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

	@Test
	public void testFindByRegionNameStartingWith() {
		List<Region> regions = regionRepository.findByRegionNameStartingWith("A");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionNameEndingWith() {
		List<Region> regions = regionRepository.findByRegionNameEndingWith("a");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionNameContaining() {
		List<Region> regions = regionRepository.findByRegionNameContaining("ca");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionNameLike() {
		List<Region> regions = regionRepository.findByRegionNameLike("Eur%");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(1);
	}

	@Test
	public void testFindByRegionNameLikeOrderByRegionName() {
		List<Region> regions = regionRepository.findByRegionNameLikeOrderByRegionName("A%");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionNameLikeOrderByRegionNameDesc() {
		List<Region> regions = regionRepository.findByRegionNameLikeOrderByRegionNameDesc("A%");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(2);
	}

	@Test
	public void testFindByRegionNameNotLike() {
		List<Region> regions = regionRepository.findByRegionNameNotLike("Amer%");
		assertThat(regions).isNotEmpty().doesNotContainNull().size().isEqualTo(3);
	}

}
