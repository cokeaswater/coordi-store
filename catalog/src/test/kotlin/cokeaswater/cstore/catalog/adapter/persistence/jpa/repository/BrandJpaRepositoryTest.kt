package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
//@DataJpaTest
//@ContextConfiguration(classes = [CatalogDomainConfiguration::class])
internal class BrandJpaRepositoryTest(
    @Autowired private val repository: BrandJpaRepository,
) {

    val log = KotlinLogging.logger {  }

    @Test
    fun testFindAll(){

        val all = repository.findAll()
        log.info { "## All Size : ${all.size}" }

        Assertions.assertEquals(9, all.size)

        all.forEach { e -> log.info { "code : ${e.code}" } }
    }

    @Test
    fun testFindBrands(){
        val result = repository.searchBrands(BrandSearchQuery(brandCode = "A"))
        log.info { "## List : $result" }
    }


}