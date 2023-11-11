package cokeaswater.cstore.catalog.application.port.`in`.params

data class BrandRegisterCommand(
    val brandCode : String,
    val name : String,
)