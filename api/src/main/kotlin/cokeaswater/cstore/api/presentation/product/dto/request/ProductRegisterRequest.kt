package cokeaswater.cstore.api.presentation.product.dto.request

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money

data class ProductRegisterRequest(
    val brandCode: String,
    val category: ProductCategory,
    val name: String,
    val price: Money
)
