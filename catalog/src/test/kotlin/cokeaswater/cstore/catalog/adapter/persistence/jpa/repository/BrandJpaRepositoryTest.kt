package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.config.CatalogDomainConfiguration
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

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
    }


}