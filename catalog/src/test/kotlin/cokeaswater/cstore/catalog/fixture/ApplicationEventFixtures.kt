package cokeaswater.cstore.catalog.fixture

import cokeaswater.cstore.catalog.application.port.`in`.event.BrandDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.ProductDomainEvent
import cokeaswater.cstore.common.event.enums.DomainState


fun createProductDomainEvent(): ProductDomainEvent {
    val product = createProductFixture()
    return ProductDomainEvent(product, DomainState.CREATED)
}

fun createBrandDomainEvent(state: DomainState? = null): BrandDomainEvent {
    val brand = createBrandFixture("A")
    return BrandDomainEvent(brand, state ?: DomainState.CREATED)
}