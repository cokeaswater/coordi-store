package cokeaswater.cstore.catalog.domain.converter

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import com.fasterxml.jackson.databind.util.StdConverter
import org.springframework.core.convert.converter.Converter

class ProductCategoryDeSerializer :
    StdConverter<String, ProductCategory>(),
    Converter<String, ProductCategory> {
    private val errorMessage: String = "제품 카테고리 인식을 실패했습니다."

    private fun convertString(value: String?): ProductCategory {
        val v = value ?: ""
        return ProductCategory.entries
            .firstOrNull { e -> e.korean == v } ?: throw IllegalArgumentException("$errorMessage : $value")
    }

    private fun convertInt(value: Number?): ProductCategory {
        val v = value ?: throw IllegalArgumentException("$errorMessage : $value")
        return ProductCategory.entries
            .firstOrNull { e -> e.order == v } ?: throw IllegalArgumentException("$errorMessage : $value")
    }

    override fun convert(source: String): ProductCategory? {
        return convertString(source)
    }

}
