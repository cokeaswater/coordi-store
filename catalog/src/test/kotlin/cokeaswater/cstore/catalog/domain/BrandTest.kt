package cokeaswater.cstore.catalog.domain

import cokeaswater.cstore.catalog.fixture.createBrandFixture
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BrandTest {

    private val brand = createBrandFixture()

    @Test
    @Order(1)
    @DisplayName("브랜드 명 변경")
    fun testChangeName() {

        val target = "GGGG"
        val registerAt = brand.registerAt

        val isDiff = target != brand.name

        val changed = brand.changeName(target)

        val lastModified = brand.lastModifiedAt

        assertEquals(isDiff, changed)
        assertEquals(true, registerAt < lastModified)

        val notChanged = brand.changeName(target)

        assertEquals(false, notChanged)
        assertEquals(true, lastModified == brand.lastModifiedAt)

    }


    @Test
    @Order(2)
    @DisplayName("브랜드 코드 변경")
    fun testChangeCode(){
        val target = "GGGG"

        val isDiff = target != brand.code

        val changed = brand.changeCode(target)

        assertEquals(isDiff, changed)

        val notChanged = brand.changeCode(target)

        assertEquals(false, notChanged)
    }

}