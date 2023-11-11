package cokeaswater.cstore.catalog.application.port.`in`.params

data class BrandModifyCommand(
    val code: String,
    val changeCode: String? = null,
    val name: String? = null
)
