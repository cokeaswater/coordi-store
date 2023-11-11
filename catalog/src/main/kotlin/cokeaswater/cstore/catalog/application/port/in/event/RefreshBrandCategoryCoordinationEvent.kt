package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.event.OperationEvent
import java.time.LocalDateTime

data class RefreshBrandCategoryCoordinationEvent(
    val brandCode: String? = null,
    val category: ProductCategory? = null,
    val price: Int? = null,
    val base: LocalDateTime = LocalDateTime.now()
) : OperationEvent()