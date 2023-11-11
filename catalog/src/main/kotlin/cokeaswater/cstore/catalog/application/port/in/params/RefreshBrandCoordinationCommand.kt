package cokeaswater.cstore.catalog.application.port.`in`.params

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import java.time.LocalDateTime

data class RefreshBrandCoordinationCommand(
    val brandCode: String? = null,
    val category: ProductCategory? = null,
    val price: Int? = null,
    val baseKey: LocalDateTime = LocalDateTime.now()
)
