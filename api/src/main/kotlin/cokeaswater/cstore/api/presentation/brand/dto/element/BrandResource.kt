package cokeaswater.cstore.api.presentation.brand.dto.element

import java.time.LocalDateTime

internal data class BrandResource(
    val id : String,
    val code: String,
    val name : String,
    val registerAt: LocalDateTime
)
