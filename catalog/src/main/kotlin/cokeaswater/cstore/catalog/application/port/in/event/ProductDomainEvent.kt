package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.catalog.domain.Product
import cokeaswater.cstore.common.event.DomainEvent

data class ProductDomainEvent(
    override val domain: Product
) : DomainEvent<Product>()