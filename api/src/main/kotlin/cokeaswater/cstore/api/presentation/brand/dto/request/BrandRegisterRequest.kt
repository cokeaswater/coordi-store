package cokeaswater.cstore.api.presentation.brand.dto.request

import jakarta.validation.constraints.NotEmpty

internal data class BrandRegisterRequest(
    @field:NotEmpty
    val code: String? = null,
    @field:NotEmpty
    val name: String? = null
)