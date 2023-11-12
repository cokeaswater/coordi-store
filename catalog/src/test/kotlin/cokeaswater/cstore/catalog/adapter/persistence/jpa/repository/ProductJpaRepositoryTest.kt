package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.ProductJpaEntity
import cokeaswater.cstore.catalog.adapter.persistence.jpa.mapper.ProductCrossJpaEntityMapper
import cokeaswater.cstore.catalog.fixture.createProductFixture
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@TestMethodOrder(OrderAnnotation::class)
//@DataJpaTest
//@ContextConfiguration(classes = [CatalogDomainConfiguration::class])
internal class ProductJpaRepositoryTest(
    @Autowired private val repository: ProductJpaRepository,
    @Autowired private val mapper: ProductCrossJpaEntityMapper

) {

    val log = KotlinLogging.logger { }

    @Test
    @Order(1)
    fun testFindAll() {
        val all: List<ProductJpaEntity> = repository.findAll()
        log.info { "## All Size : ${all.size}" }

        assertEquals(72, all.size)
    }


    @Test
    @Order(2)
    @Transactional
    fun testInsertAndUpdate() {

        // Insert And Check
        val domain = createProductFixture()
        val entity = mapper.productToEntity(domain)
        repository.save(entity)

        val all = repository.findAll();
        log.info { "## All Size : ${all.size}" }

        assertEquals(73, all.size)
        val found = checkNotNull(repository.findByIdOrNull(domain.id)) { "Not Found" }

        assertEquals(domain.name, found.name);

        // Update And Check
        val changeName = "ChangeTest"
        domain.changeName(changeName)

        val forUpdate = mapper.productToEntity(domain);
        repository.save(forUpdate)

        val updateFound = checkNotNull(repository.findByIdOrNull(forUpdate.id)) { "Not Found" }
        assertEquals(changeName, updateFound.name)

    }

    @Test
    @Order(3)
    @Transactional
    fun testDeleteByBrandCode() {

        val count = repository.deleteProductsByBrandCode("A")
        log.info { "## Deleted Count : $count" }
        assertEquals(8, count)
    }
}