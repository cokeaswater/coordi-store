package cokeaswater.cstore.catalog.application.port.`in`.params

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money

data class ProductModifyCommand(
    val productId: String,
    val brandCode: String?,
    val category: ProductCategory?,
    val name: String?,
    val price: Money?
)
