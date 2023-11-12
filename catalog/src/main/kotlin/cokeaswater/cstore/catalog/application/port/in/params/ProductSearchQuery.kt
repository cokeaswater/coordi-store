package cokeaswater.cstore.catalog.application.port.`in`.params

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class ProductSearchQuery(
    val brandCode: String? = null,
    val category: ProductCategory? = null,
    val pageable: Pageable = PageRequest.of(0, 10)
)
