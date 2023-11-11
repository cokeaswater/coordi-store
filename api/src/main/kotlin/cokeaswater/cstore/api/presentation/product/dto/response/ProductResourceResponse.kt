package cokeaswater.cstore.api.presentation.product.dto.response

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import java.time.LocalDateTime

internal data class ProductResourceResponse(
    val id: String,
    val brandId: String,
    val category: ProductCategory,
    val name: String,
    val price: Money,
    val registerAt: LocalDateTime
)
