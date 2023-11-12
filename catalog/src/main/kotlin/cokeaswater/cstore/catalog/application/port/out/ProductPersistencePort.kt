package cokeaswater.cstore.catalog.application.port.out

import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.domain.Product

interface ProductPersistencePort {

    fun findAll(): List<Product>
    fun searchProducts(query: ProductSearchQuery): List<Product>

    fun findProductById(productId: String): Product?

    fun saveProduct(product: Product): Product

    fun deleteProduct(product: Product)

    fun deleteProductByBrandCode(brandCode: String): Long
}