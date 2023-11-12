package cokeaswater.cstore.api.presentation.product.dto.request

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "제품 수정 요청")
internal data class ProductModifyRequest(
    @field:Schema(type = "string" , required = false , description = "브랜드코드", example = "D")
    val brandCode: String? = null,
    @field:Schema(type = "string" , required = false , description = "카테고리", example = "양말")
    val category: ProductCategory? = null,

    @field:Schema(type = "string" , required = false , description = "이름", example = "더 싸구려 양말")
    val name: String? = null,

    @field:Schema(type = "int" , required = false , description = "가격" , example = "500")
    val price: Money? = null
)
