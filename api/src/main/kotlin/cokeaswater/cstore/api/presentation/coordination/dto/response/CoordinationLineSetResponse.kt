package cokeaswater.cstore.api.presentation.coordination.dto.response

import cokeaswater.cstore.api.presentation.coordination.dto.element.CoordinationLine
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CoordinationLineSetResponse(

    @JsonProperty("카테고리")
    val category: ProductCategory? = null,

    @JsonProperty("최저가")
    val lowest: List<CoordinationLine>? = null,

    @JsonProperty("최고가")
    val highest: List<CoordinationLine>? = null
)
