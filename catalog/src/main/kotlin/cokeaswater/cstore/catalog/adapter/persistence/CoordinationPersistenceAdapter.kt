package cokeaswater.cstore.catalog.adapter.persistence

import cokeaswater.cstore.catalog.adapter.persistence.mybatis.repository.CoordinationMybatisRepository
import cokeaswater.cstore.catalog.application.port.`in`.dto.BrandCoordinationScoreDto
import cokeaswater.cstore.catalog.application.port.`in`.dto.CoordinationProductDto
import cokeaswater.cstore.catalog.application.port.out.CoordinationPersistencePort
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
internal class CoordinationPersistenceAdapter(
    private val repository: CoordinationMybatisRepository
) : CoordinationPersistencePort {


    override fun findLastCategoryCoordinationsPartitionKey(): LocalDateTime? {
        return repository.findLastCategoryCoordinationsPartitionKey()
    }

    override fun findLastBrandRecommendPartitionKey(): LocalDateTime? {
        return repository.findLastBrandRecommendPartitionKey()
    }

    override fun findLowestPriceRecommendBrand(): BrandCoordinationScoreDto? {
        return repository.findLowestPriceRecommendBrand()
    }

    override fun findLastBrandCategoryCoordinationsPartitionKey(brandCode: String): LocalDateTime? {
        return repository.findLastBrandCategoryCoordinationsPartitionKey(brandCode)
    }


    override fun findBrandCategoryCoordinations(
        brandCode: String, key: LocalDateTime
    ): List<CoordinationProductDto> {
        return repository.findBrandCategoryCoordinations(brandCode, key)
    }

    override fun findCategoryCoordinations(
        category: ProductCategory,
        key: LocalDateTime
    ): List<CoordinationProductDto> {
        return repository.findCategoryCoordinations(category, key);
    }

    override fun findLowestPriceBrandCategoryCoordinationSet(
        brandCode: String, key: LocalDateTime
    ): List<CoordinationProductDto> {
        return repository.findLowestPriceBrandCategoryCoordinationSet(brandCode, key)
    }

    override fun findLowestPriceCategoryCoordinationSet(key: LocalDateTime): List<CoordinationProductDto> {
        return repository.findLowestPriceCategoryCoordinationSet(key)
    }

    override fun refreshBrandTotalCoordinationScore(brandCode: String?): Int {
        return repository.refreshBrandTotalCoordinationScore(brandCode)
    }

    override fun refreshCategoryCoordinations(now: LocalDateTime): Int {
        return repository.refreshCategoryCoordinations(now)
    }

    override fun refreshBrandCoordinations(brandCode: String?, key: LocalDateTime): Int {
        return repository.refreshBrandCategoryCoordinations(brandCode, key)
    }
}