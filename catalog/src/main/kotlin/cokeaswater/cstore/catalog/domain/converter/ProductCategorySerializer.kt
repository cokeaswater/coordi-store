package cokeaswater.cstore.catalog.domain.converter

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import com.fasterxml.jackson.databind.util.StdConverter

class ProductCategorySerializer : StdConverter<ProductCategory, String>() {
    private val errorMessage: String = "제품 카테고리 인식을 실패했습니다."

    override fun convert(value: ProductCategory?): String {
        return value?.korean ?: ""
    }

}