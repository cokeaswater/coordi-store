package cokeaswater.cstore.catalog.application.port.`in`.params

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class BrandSearchQuery(
    val brandCode: String? = null,
    val pageable: Pageable = PageRequest.of(0, 10)
)
