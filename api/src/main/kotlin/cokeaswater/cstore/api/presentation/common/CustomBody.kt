package cokeaswater.cstore.api.presentation.common

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CustomBody(
    val isSuccess: Boolean? = true,
    val message: String? = null,
    val result: Any? = null
)
