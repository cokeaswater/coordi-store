package cokeaswater.cstore.catalog.adapter.persistence.mybatis.repository

import cokeaswater.cstore.catalog.config.CatalogDomainConfiguration
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@SpringBootTest
@DisplayName("코디네이션 MyBATIS 저장소")
internal class CoordinationMybatisRepositoryTest(
    @Autowired val repository: CoordinationMybatisRepository
) {

    private val log = KotlinLogging.logger {  }


    @Test
    fun testFindLastCategoryCoordinationsPartitionKey() {
        val key = repository.findLastCategoryCoordinationsPartitionKey()
        log.info { "## Key : $key" }
        Assertions.assertNull(key)
    }

    @Test
    fun testFindLastBrandCategoryCoordinationsPartitionKey() {
        val key = repository.findLastBrandCategoryCoordinationsPartitionKey("AA")
        log.info { "## Key : $key" }
        Assertions.assertNull(key)
    }

    @Test
    fun testFindBrandCategoryCoordinations(){
        val list = repository.findBrandCategoryCoordinations("", LocalDateTime.now())
        log.info { "## List : ${list.size}" }
        Assertions.assertEquals(0, list.size)
    }

    @Test
    fun testFindCategoryCoordinations(){
        val list = repository.findCategoryCoordinations(ProductCategory.ACC, LocalDateTime.now())
        log.info { "## List : ${list.size}" }
        Assertions.assertEquals(0, list.size)
    }

    @Test
    fun testFindLowestPriceRecommendBrand(){
        val brandScore = repository.findLowestPriceRecommendBrand()
        log.info { "## Brand : $brandScore" }
        Assertions.assertNull(brandScore)
    }

    @Test
    fun testFindLowestPriceBrandCategoryCoordinationSet(){
        val list = repository.findLowestPriceBrandCategoryCoordinationSet("", LocalDateTime.now())
        log.info { "## List : ${list.size}" }
        Assertions.assertEquals(0, list.size)
    }

    @Test
    @Transactional
    fun testFindLowestPriceCategoryCoordinationSet(){
        val list = repository.findLowestPriceCategoryCoordinationSet(LocalDateTime.now())
        log.info { "## List : ${list.size}" }
        Assertions.assertEquals(0, list.size)

    }

    @Test
    @Transactional
    fun refreshBrandCategoryCoordinations(){
        val now = LocalDateTime.now()
        val inserted = repository.refreshBrandCategoryCoordinations(key = now)
        log.info { "## inserted : $inserted" }
        Assertions.assertTrue(inserted > 0)

        val list = repository.findLowestPriceBrandCategoryCoordinationSet("A", now)
        log.info { "## List : ${list.size}" }
        log.info { "## List : $list" }
        Assertions.assertEquals(8 , list.size)

    }

    @Test
    @Transactional
    fun testRefreshCategoryCoordinations(){
        val inserted = repository.refreshCategoryCoordinations(LocalDateTime.now())
        log.info { "## inserted : $inserted" }
        Assertions.assertTrue(inserted == 0);
    }

    @Test
    @Transactional
    fun testRefreshBrandTotalCoordinationScore() {
        val inserted = repository.refreshBrandTotalCoordinationScore(null)
        log.info { "## inserted : $inserted" }
        Assertions.assertTrue(inserted == 0);
    }

}