package cokeaswater.cstore.catalog.fixture

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.domain.Brand
import cokeaswater.cstore.catalog.domain.Product
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import java.time.LocalDateTime
import kotlin.random.Random


val random: Random = Random.Default

fun createProductFixture(): Product {
    return Product.generateNew(
        name = random.nextInt().toString(),
        price = Money.of(random.nextInt(10000, 20000)),
        category = ProductCategory.ACC,
        brand = createBrandFixture(null)
    )
}


fun createBrandFixture(
    code: String? = null
): Brand {
    return Brand.generateNew(
        code = code ?: random.nextInt().toString(),
        name = random.nextInt().toString()
    )
}

fun createBrandFixtureByRegisterCommand(
    command: BrandRegisterCommand
): Brand {
    return Brand(
        id = random.nextInt().toString(),
        name = command.name,
        code = command.brandCode,
        registerAt = LocalDateTime.now(),
        lastModifiedAt = LocalDateTime.now()
    )
}