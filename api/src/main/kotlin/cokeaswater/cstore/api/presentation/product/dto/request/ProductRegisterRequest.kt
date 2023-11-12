package cokeaswater.cstore.api.presentation.product.dto.request

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springdoc.core.annotations.ParameterObject


@Schema(description = "제품 등록 요청")
internal data class ProductRegisterRequest(
    @field:Schema(type = "string" , required = true , description = "브랜드코드", example = "D")
    @field:NotEmpty
    val brandCode: String? = null,

    @field:NotNull
    @field:Schema(type = "string" , required = true , description = "카테고리", example = "양말")
    val category: ProductCategory? = null,

    @field:NotEmpty
    @field:Schema(type = "string" , required = true , description = "이름", example = "싸구려 양말")
    val name: String? = null,

    @field:Schema(type = "int" , required = true , description = "가격" , example = "1000")
    @field:NotNull
    val price: Money? = null
)
