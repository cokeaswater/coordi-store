package cokeaswater.cstore.catalog.domain.enums

import cokeaswater.cstore.catalog.domain.converter.ProductCategoryDeSerializer
import cokeaswater.cstore.catalog.domain.converter.ProductCategorySerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize


@JsonSerialize(converter = ProductCategorySerializer::class)
@JsonDeserialize(converter = ProductCategoryDeSerializer::class)
enum class ProductCategory(
    val order: Int,
    val korean: String

) {

    TOP(1, "상의"),
    OUTER(2, "아우터"),
    PANTS(3, "바지"),
    SNEAKERS(4, "스니커즈"),
    BAG(5, "가방"),
    HAT(6, "모자"),
    SOCKS(7, "양말"),
    ACC(8, "액세서리");


}