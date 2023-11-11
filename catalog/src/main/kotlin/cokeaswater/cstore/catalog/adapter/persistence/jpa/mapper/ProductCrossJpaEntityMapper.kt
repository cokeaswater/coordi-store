package cokeaswater.cstore.catalog.adapter.persistence.jpa.mapper

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.ProductJpaEntity
import cokeaswater.cstore.catalog.domain.Product
import cokeaswater.cstore.common.domain.Money
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
internal class ProductCrossJpaEntityMapper {

    private val log = KotlinLogging.logger { }

    fun productToEntity(product: Product): ProductJpaEntity {
        return ProductJpaEntity(
            id = product.id,
            brandCode = product.brandCode,
            category = product.category,
            name = product.name,
            price = product.price.value,
            registerAt = product.registerAt,
            lastModifiedAt = product.lastModifiedAt
        )
    }

    fun entityToProduct(entity: ProductJpaEntity): Product {
        return Product(
            id = entity.id,
            brandCode = entity.brandCode,
            category = entity.category,
            name = entity.name,
            price = Money.of(entity.price),
            registerAt = entity.registerAt,
            lastModifiedAt = entity.lastModifiedAt
        )
    }

    fun entitiesToProducts(entities: List<ProductJpaEntity>): List<Product> {
        return entities.map { e -> entityToProduct(e) }
    }
}