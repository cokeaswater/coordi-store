package cokeaswater.cstore.api.presentation.product

import cokeaswater.cstore.api.presentation.product.dto.element.ProductResource
import cokeaswater.cstore.catalog.domain.Product
import org.springframework.stereotype.Component

@Component
internal class ProductToResponseMapper {


    fun productToResponseResource(product: Product): ProductResource {
        return ProductResource(
            id = product.id,
            brandId = product.brandCode,
            category = product.category,
            name = product.name,
            price = product.price,
            registerAt = product.registerAt
        )
    }

    fun productsToResponseResources(products: List<Product>): List<ProductResource> {
        return products.map { e -> productToResponseResource(e) }
    }
}