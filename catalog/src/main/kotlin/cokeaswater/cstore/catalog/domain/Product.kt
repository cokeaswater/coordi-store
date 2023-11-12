package cokeaswater.cstore.catalog.domain

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import com.github.f4b6a3.ulid.UlidCreator
import java.time.LocalDateTime

class Product
internal constructor(
    val id: String,
    brandCode: String,
    category: ProductCategory,
    name: String,
    price: Money,
    val registerAt: LocalDateTime,
    lastModifiedAt: LocalDateTime
) {
    var brandCode: String = brandCode
        private set

    var category: ProductCategory = category
        private set

    var name: String = name
        private set

    var price: Money = price
        private set

    var lastModifiedAt: LocalDateTime = lastModifiedAt
        private set


    internal fun changeBrand(brand: Brand): Boolean {
        val isDiff = this.brandCode != brand.code
        if (isDiff) {
            this.brandCode = brand.code
            modified()
        }
        return isDiff
    }

    internal fun changeName(name: String): Boolean {
        val isDiff = this.name != name
        if (isDiff) {
            this.name = name
            modified()
        }
        return isDiff
    }

    internal fun changeCategory(category: ProductCategory): Boolean {
        val isDiff = this.category != category
        if (isDiff) {
            this.category = category;
            modified()
        }
        return isDiff
    }

    internal fun changePrice(price: Money): Boolean {
        val isDiff = this.price.value != price.value
        if (isDiff) {
            this.price = price
            modified()
        }
        return isDiff
    }

    internal fun deleted() {
        modified()
    }

    private fun modified() {
        this.lastModifiedAt = LocalDateTime.now()
    }

    companion object Builder {
        internal fun generateNew(
            brand: Brand,
            category: ProductCategory,
            name: String,
            price: Money,
        ): Product {
            val now = LocalDateTime.now()
            return Product(
                id = UlidCreator.getMonotonicUlid().toString(),
                brandCode = brand.code,
                category = category,
                name = name,
                price = price,
                registerAt = now,
                lastModifiedAt = now
            )
        }
    }

}