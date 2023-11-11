package cokeaswater.cstore.catalog.application.port.`in`.dto

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import java.time.LocalDateTime

data class CoordinationProductDto(
    val productId: String,
    val brandCode: String,
    val category: ProductCategory,
    val price: Int,
    val key: LocalDateTime
)
