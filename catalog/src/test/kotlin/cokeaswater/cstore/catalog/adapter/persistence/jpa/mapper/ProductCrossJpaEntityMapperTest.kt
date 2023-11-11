package cokeaswater.cstore.catalog.adapter.persistence.jpa.mapper

import cokeaswater.cstore.catalog.fixture.createProductFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProductCrossJpaEntityMapperTest {

    private val mapper: ProductCrossJpaEntityMapper = ProductCrossJpaEntityMapper()

    @Test
    fun testProductToJpaEntity() {

        val product = createProductFixture();

        val entity = mapper.productToEntity(product)

        assertEquals(product.id, entity.id)
        assertEquals(product.name, entity.name)
        assertEquals(product.brandCode, entity.brandCode)
        assertEquals(product.price.value, entity.price)
        assertEquals(product.category, entity.category)
        assertEquals(product.registerAt, entity.registerAt)

    }


}