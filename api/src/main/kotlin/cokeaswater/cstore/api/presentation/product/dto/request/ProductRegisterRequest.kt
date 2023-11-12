package cokeaswater.cstore.api.presentation.product.dto.request

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

internal data class ProductRegisterRequest(
    @field:NotEmpty
    val brandCode: String? = null,
    @field:NotNull
    val category: ProductCategory? = null,
    @field:NotEmpty
    val name: String? = null,
    @field:NotNull
    val price: Money? = null
)
