package cokeaswater.cstore.catalog.domain

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.catalog.fixture.createBrandFixture
import cokeaswater.cstore.catalog.fixture.createProductFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(OrderAnnotation::class)
class ProductTest {

    private val product: Product = createProductFixture()


    @Order(1)
    @Test
    @DisplayName("제품명 변경")
    fun testChangeName() {
        val target = "GGG"
        val registerAt = product.registerAt

        val isDiff = target != product.name
        val changed = product.changeName(target)

        val modified = product.lastModifiedAt

        assertEquals(isDiff, changed)
        assertEquals(true, registerAt < modified)

        val notChanged = product.changeName(target)

        assertEquals(false, notChanged)
        assertEquals(true, modified == product.lastModifiedAt)
    }


    @Test
    @Order(2)
    @DisplayName("제품 브랜드 변경")
    fun testChangeBrand() {
        val target = createBrandFixture()

        val isDiff = target.code != product.brandCode
        val changed = product.changeBrand(target)

        assertEquals(isDiff, changed)

        val notChanged = product.changeBrand(target)

        assertEquals(false, notChanged)
    }


    @Test
    @Order(3)
    @DisplayName("제품 카테고리 변경")
    fun testChangeCategory() {
        val target = ProductCategory.BAG

        val isDiff = target != product.category
        val changed = product.changeCategory(target)

        assertEquals(isDiff, changed)

        val notChanged = product.changeCategory(target)

        assertEquals(false, notChanged)

    }
}