package cokeaswater.cstore.catalog.application.port.`in`.dto

import java.time.LocalDateTime

data class BrandCoordinationScoreDto(
    val brandCode: String,
    val sumOfCategoryPrice: Int,
    val countOfCategory: Int,
    val averagePrice: Int,
    val scoreKey: LocalDateTime
)