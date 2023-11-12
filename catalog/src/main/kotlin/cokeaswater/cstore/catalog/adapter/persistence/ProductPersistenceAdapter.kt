package cokeaswater.cstore.catalog.adapter.persistence

import cokeaswater.cstore.catalog.adapter.persistence.jpa.mapper.ProductCrossJpaEntityMapper
import cokeaswater.cstore.catalog.adapter.persistence.jpa.repository.ProductJpaRepository
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.application.port.out.ProductPersistencePort
import cokeaswater.cstore.catalog.domain.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class ProductPersistenceAdapter(
    private val repository: ProductJpaRepository,
    private val mapper: ProductCrossJpaEntityMapper
) : ProductPersistencePort {
    override fun findAll(): List<Product> {
        val list = repository.findAll()
        return mapper.entitiesToProducts(list)
    }

    override fun searchProducts(query: ProductSearchQuery): List<Product> {
        val list = repository.searchProducts(query)
        return mapper.entitiesToProducts(list)
    }

    override fun findProductById(productId: String): Product? {

        val entity = repository.findByIdOrNull(productId) ?: return null


        return mapper.entityToProduct(entity);
    }

    override fun saveProduct(product: Product): Product {
        val entity = mapper.productToEntity(product)
        repository.save(entity)
        val product = mapper.entityToProduct(entity)
        return product
    }

    override fun deleteProduct(product: Product) {
        repository.deleteById(product.id)
    }

    override fun deleteProductByBrandCode(brandCode: String): Long {
        return repository.deleteProductsByBrandCode(brandCode)
    }
}