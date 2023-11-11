package cokeaswater.cstore.catalog.domain.converter

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductCategorySerializerTest{

    private val serializer = ProductCategorySerializer()
    @Test
    fun testSerialize(){
        val top = serializer.convert(ProductCategory.TOP)
        assertEquals(ProductCategory.TOP.korean, top)

        val outer = serializer.convert(ProductCategory.OUTER)
        assertEquals(ProductCategory.OUTER.korean, outer)

        val pants = serializer.convert(ProductCategory.PANTS)
        assertEquals(ProductCategory.PANTS.korean, pants)

        val sneakers = serializer.convert(ProductCategory.SNEAKERS)
        assertEquals(ProductCategory.SNEAKERS.korean, sneakers)

        val bag = serializer.convert(ProductCategory.BAG)
        assertEquals(ProductCategory.BAG.korean, bag)

        val hat = serializer.convert(ProductCategory.HAT)
        assertEquals(ProductCategory.HAT.korean, hat)

        val socks = serializer.convert(ProductCategory.SOCKS)
        assertEquals(ProductCategory.SOCKS.korean, socks)

        val acc = serializer.convert(ProductCategory.ACC)
        assertEquals(ProductCategory.ACC.korean, acc)

    }
}