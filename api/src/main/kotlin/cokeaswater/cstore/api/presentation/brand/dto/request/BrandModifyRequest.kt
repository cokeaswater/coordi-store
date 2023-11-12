package cokeaswater.cstore.api.presentation.brand.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "브랜드 수정 요청")
internal data class BrandModifyRequest(
    @field:Schema(type = "string", required = false, description = "이름", example = "더 싸구려 브랜드")
    val name: String? = null,
    @field:Schema(type = "string", required = false, description = "변경할 브랜드코드", example = "AAA")
    val changeCode: String? = null
)