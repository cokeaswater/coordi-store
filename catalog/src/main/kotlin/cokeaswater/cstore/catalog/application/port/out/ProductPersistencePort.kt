package cokeaswater.cstore.catalog.application.port.out

import cokeaswater.cstore.catalog.domain.Product

interface ProductPersistencePort {

    fun findAll(): List<Product>

    fun findProductById(productId: String): Product?

    fun saveProduct(product: Product): Product

    fun deleteProduct(product: Product)

    fun deleteProductByBrandCode(brandCode : String) : Long
}