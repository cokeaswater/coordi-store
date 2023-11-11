package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.application.port.`in`.dto.CoordinationProductDto
import cokeaswater.cstore.catalog.domain.enums.ProductCategory

interface CoordinationQueryCase {


    fun querySummaryRecommendCoordination(): List<CoordinationProductDto>

    fun queryBrandRecommendCoordination(): List<CoordinationProductDto>

    fun queryCategoryMinMaxCoordination(category: ProductCategory): List<CoordinationProductDto>

}