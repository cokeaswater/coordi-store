package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.fixture.createBrandModifyCommandFixture
import cokeaswater.cstore.catalog.fixture.createBrandRegisterCommandFixture
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@DisplayName("브랜드 서비스 통합 테스트")
internal class BrandServiceIntegrationTest(
    @Autowired private val service: BrandService
) {

    private val log = KotlinLogging.logger { }


    @Test
    @DisplayName("코드로 브랜드 찾기")
    fun testGetBrandByCode() {

        val brand = service.getBrandByCode("A")
        assertEquals("A", brand.code)

        assertThrows(IllegalStateException::class.java) { service.getBrandByCode("ZZZ") }
    }

    @Test
    @DisplayName("브랜드 등록")
    @Transactional
    fun testRegisterBrand() {
        val cmd = createBrandRegisterCommandFixture()

        val brand = service.registerBrand(cmd)
        assertEquals(cmd.brandCode, brand.code)
        assertEquals(cmd.name, brand.name)

        assertThrows(java.lang.IllegalStateException::class.java) {
            service.registerBrand(cmd)
        }
    }

    @Test
    @DisplayName("브랜드 수정")
    @Transactional
    fun testModifyBrand(){
        val cmd = createBrandModifyCommandFixture("A")

        val brand = service.modifyBrand(cmd)
        assertEquals(cmd.changeCode, brand.code)
        assertEquals(cmd.name, brand.name)

        assertThrows(IllegalStateException::class.java) {
            val c = createBrandModifyCommandFixture("FFF")
            service.modifyBrand(c)
        }
    }

    @Test
    @DisplayName("브랜드 삭제")
    @Transactional
    fun testRemoveBrand(){

        service.removeBrand("A")

        service.removeBrand("AAA")
    }

}