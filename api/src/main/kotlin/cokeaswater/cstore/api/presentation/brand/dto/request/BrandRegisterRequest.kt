package cokeaswater.cstore.api.presentation.brand.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

@Schema(description = "브랜드 등록 요청")
internal data class BrandRegisterRequest(
    @field:Schema(type = "string", required = true, description = "브랜드코드", example = "AAA")
    @field:NotEmpty
    val code: String? = null,
    @field:Schema(type = "string", required = true, description = "이름", example = "싸구려 브랜드")
    @field:NotEmpty
    val name: String? = null
)