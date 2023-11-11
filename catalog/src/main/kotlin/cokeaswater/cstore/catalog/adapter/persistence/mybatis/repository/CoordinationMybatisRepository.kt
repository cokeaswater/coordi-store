package cokeaswater.cstore.catalog.adapter.persistence.mybatis.repository

import cokeaswater.cstore.catalog.application.port.`in`.dto.BrandCoordinationScoreDto
import cokeaswater.cstore.catalog.application.port.`in`.dto.CoordinationProductDto
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.time.LocalDateTime


@Mapper
internal interface CoordinationMybatisRepository {

    fun findLastCategoryCoordinationsPartitionKey(): LocalDateTime?

    fun findLastBrandCategoryCoordinationsPartitionKey(@Param("brandCode") brandCode: String): LocalDateTime?

    fun findBrandCategoryCoordinations(
        @Param("brandCode") brandCode: String,
        @Param("key") key: LocalDateTime
    ): List<CoordinationProductDto>

    fun findCategoryCoordinations(
        @Param("category") category: ProductCategory,
        @Param("key") key: LocalDateTime
    ): List<CoordinationProductDto>

    fun findLowestPriceRecommendBrand(): BrandCoordinationScoreDto?

    fun findLowestPriceBrandCategoryCoordinationSet(
        @Param("brandCode") brandCode: String,
        @Param("key") key: LocalDateTime
    ): List<CoordinationProductDto>

    fun findLowestPriceCategoryCoordinationSet(@Param("key") key: LocalDateTime): List<CoordinationProductDto>

    fun refreshBrandCategoryCoordinations(
        @Param("brandCode") brandCode: String? = null,
        @Param("key") key: LocalDateTime
    ): Int

    fun refreshCategoryCoordinations(@Param("key") key: LocalDateTime): Int

    fun refreshBrandTotalCoordinationScore(@Param("brandCode") brandCode: String?): Int


}