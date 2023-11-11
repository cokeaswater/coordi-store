package cokeaswater.cstore.api.presentation.coordination.dto.element

import cokeaswater.cstore.api.jsonview.CoordinationView
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

data class CoordinationLine(

    @JsonView(value = [CoordinationView.CategoryInclude::class])
    @JsonProperty("카테고리")
    val category: ProductCategory,

    @JsonProperty("가격")
    val price: Money,

    @JsonView(value = [CoordinationView.BrandInclude::class])
    @JsonProperty("브랜드")
    val brandCode: String
)