package cokeaswater.cstore.catalog.domain.converter

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ProductCategoryDeSerializerTest {
    private val deSerializer = ProductCategoryDeSerializer()



    @Test
    fun testDeSerialize(){
        val top = deSerializer.convert("상의")
        assertEquals(ProductCategory.TOP, top)

        val outer = deSerializer.convert("아우터")
        assertEquals(ProductCategory.OUTER, outer)

        val pants = deSerializer.convert("바지")
        assertEquals(ProductCategory.PANTS, pants)

        val sneakers = deSerializer.convert("스니커즈")
        assertEquals(ProductCategory.SNEAKERS, sneakers)

        val bag = deSerializer.convert("가방")
        assertEquals(ProductCategory.BAG, bag)

        val hat = deSerializer.convert("모자")
        assertEquals(ProductCategory.HAT, hat)

        val socks = deSerializer.convert("양말")
        assertEquals(ProductCategory.SOCKS, socks)

        val acc = deSerializer.convert("액세서리")
        assertEquals(ProductCategory.ACC, acc)

        assertThrows(IllegalArgumentException::class.java) {
            deSerializer.convert("FFF")
        }


    }

}