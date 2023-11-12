package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.catalog.fixture.createProductModifyCommandFixture
import cokeaswater.cstore.catalog.fixture.createProductRegisterCommandFixture
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException


@SpringBootTest
@DisplayName("제품 서비스 통합 테스트")
internal class ProductServiceIntegrationTest(
    @Autowired private val service: ProductService
) {

    private val log = KotlinLogging.logger {  }

    @Test
    @DisplayName("제품 검색")
    fun testSearchProducts() {

        val query = ProductSearchQuery(brandCode = "A")
        val searched = service.searchProduct(query)
        assertEquals(8, searched.size)

        val query2 = ProductSearchQuery(category = ProductCategory.ACC)
        val searched2 = service.searchProduct(query2)
        assertEquals(9, searched2.size)
    }

    @Test
    @Transactional
    @DisplayName("제품 등록")
    fun testRegisterProduct(){
        val cmd = createProductRegisterCommandFixture("A")

        val product = service.registerProduct(cmd)
        assertEquals(cmd.brandCode, product.brandCode)
        assertEquals(cmd.name, product.name)
        assertEquals(cmd.category, product.category)
        assertEquals(cmd.price, product.price)

        val notBrandCmd = createProductRegisterCommandFixture("AAA")
        assertThrows(IllegalStateException::class.java) {

            service.registerProduct(notBrandCmd)
        }
    }

    @Test
    @Transactional
    @DisplayName("제품 수정")
    fun testModifyProduct() {
        val rcmd = createProductRegisterCommandFixture("A")

        val product = service.registerProduct(rcmd)

        val cmd = createProductModifyCommandFixture(productId = product.id, brandCode = "B")

        val modified = service.modifyProduct(cmd)
        assertEquals(cmd.brandCode, modified.brandCode)
        assertEquals(cmd.name, modified.name)
        assertEquals(cmd.category, modified.category)
        assertEquals(cmd.price, modified.price)

        val notExistBrand = createProductModifyCommandFixture(productId = product.id, brandCode = "FFF")
        assertThrows(IllegalStateException::class.java){
            service.modifyProduct(notExistBrand)
        }


        val notExistCmd = createProductModifyCommandFixture(productId = "AAA")
        assertThrows(IllegalStateException::class.java){
            service.modifyProduct(notExistCmd)
        }
    }

    @Test
    @Transactional
    @DisplayName("제품 수정")
    fun testRemoveProduct(){

        service.removeProduct("AAA")
    }

}