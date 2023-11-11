package cokeaswater.cstore.api.presentation.coordination.dto.response

import cokeaswater.cstore.api.presentation.coordination.dto.element.CategorySetBase
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CategorySetCoordinationResponse(

    @JsonProperty("최저가")
    val lowPrice: CategorySetBase = CategorySetBase()
)
