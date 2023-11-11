package cokeaswater.cstore.api.presentation.brand.dto.response

import java.time.LocalDateTime

internal data class BrandResourceResponse(
    val id : String,
    val code: String,
    val name : String,
    val registerAt: LocalDateTime
)
