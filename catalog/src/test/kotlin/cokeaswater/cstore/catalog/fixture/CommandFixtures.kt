package cokeaswater.cstore.catalog.fixture

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductRegisterCommand
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money


fun createBrandRegisterCommandFixture(): BrandRegisterCommand {
    return BrandRegisterCommand(
        brandCode = random.nextInt().toString(),
        name = random.nextInt().toString()
    )
}

fun createBrandModifyCommandFixture(code: String): BrandModifyCommand {
    return BrandModifyCommand(
        code = code,
        name = random.nextInt().toString(),
        changeCode = random.nextInt().toString(),
    )
}

fun createProductRegisterCommandFixture(brandCode: String? = null): ProductRegisterCommand {
    return ProductRegisterCommand(
        brandCode = brandCode ?: random.nextInt().toString(),
        name = random.nextInt().toString(),
        price = Money.of(random.nextInt()),
        category = ProductCategory.entries.firstOrNull() { e -> e.order == random.nextInt(2, 8) } ?: ProductCategory.ACC
    )
}

fun createProductModifyCommandFixture(productId: String? = null, brandCode: String? = null): ProductModifyCommand {
    return ProductModifyCommand(
        productId = productId ?: random.nextInt().toString(),
        brandCode = brandCode ?: random.nextInt().toString(),
        name = random.nextInt().toString(),
        price = Money.of(random.nextInt()),
        category = ProductCategory.entries.firstOrNull() { e -> e.order == random.nextInt(2, 8) } ?: ProductCategory.ACC
    )
}