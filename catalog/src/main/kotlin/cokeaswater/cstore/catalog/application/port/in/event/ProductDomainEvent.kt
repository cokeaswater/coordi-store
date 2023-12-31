package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.catalog.domain.Product
import cokeaswater.cstore.common.event.DomainEvent
import cokeaswater.cstore.common.event.enums.DomainState

data class ProductDomainEvent(
    override val domain: Product,
    override val state: DomainState
) : DomainEvent<Product>()