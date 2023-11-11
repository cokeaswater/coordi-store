package cokeaswater.cstore.catalog.application.port.out

import cokeaswater.cstore.catalog.application.port.`in`.dto.BrandCoordinationScoreDto
import cokeaswater.cstore.catalog.application.port.`in`.dto.CoordinationProductDto
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import java.time.LocalDateTime

internal interface CoordinationPersistencePort {

    fun findLastCategoryCoordinationsPartitionKey(): LocalDateTime?

    fun findLowestPriceRecommendBrand(): BrandCoordinationScoreDto?

    fun findLastBrandCategoryCoordinationsPartitionKey(brandCode: String): LocalDateTime?

    fun findBrandCategoryCoordinations(brandCode: String, key: LocalDateTime): List<CoordinationProductDto>

    fun findCategoryCoordinations(category: ProductCategory, key: LocalDateTime): List<CoordinationProductDto>

    fun findLowestPriceBrandCategoryCoordinationSet(brandCode: String, key: LocalDateTime): List<CoordinationProductDto>

    fun findLowestPriceCategoryCoordinationSet(key: LocalDateTime): List<CoordinationProductDto>

    fun refreshBrandTotalCoordinationScore(brandCode: String?): Int

    fun refreshCategoryCoordinations(now: LocalDateTime): Int

    fun refreshBrandCoordinations(brandCode: String?, key: LocalDateTime): Int
}