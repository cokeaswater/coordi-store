package cokeaswater.cstore.api.presentation.product.dto.request

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money

internal data class ProductModifyRequest(
    val brandCode: String? = null,
    val category: ProductCategory? = null,
    val name: String? = null,
    val price: Money? = null
)
