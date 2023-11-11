package cokeaswater.cstore.api.presentation.product

import cokeaswater.cstore.api.presentation.product.dto.response.ProductResourceResponse
import cokeaswater.cstore.catalog.domain.Product
import org.springframework.stereotype.Component

@Component
internal class ProductToResponseMapper {


    fun productToResponse(product: Product): ProductResourceResponse {
        return ProductResourceResponse(
            id = product.id,
            brandId = product.brandCode,
            category = product.category,
            name = product.name,
            price = product.price,
            registerAt = product.registerAt
        )
    }
}