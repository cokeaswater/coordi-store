package cokeaswater.cstore.catalog

import cokeaswater.cstore.catalog.config.CatalogDomainConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [CatalogDomainConfiguration::class])
class CatalogApplicationTests {

    @Test
    fun contextLoads() {
    }

}
