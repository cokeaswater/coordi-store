package cokeaswater.cstore.api.presentation.coordination.dto.element

import cokeaswater.cstore.common.domain.Money
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CategorySetBase(
    @JsonProperty("브랜드")
    val brand: String? = null,
    @JsonProperty("카테고리")
    val category: List<CoordinationLine>? = null,
    @JsonProperty("총액")
    val total: Money? = null
)
