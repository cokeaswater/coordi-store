package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.catalog.domain.Brand
import cokeaswater.cstore.common.event.DomainEvent
import cokeaswater.cstore.common.event.enums.DomainState

data class BrandDomainEvent(
    override val domain: Brand,
    override val state: DomainState
) : DomainEvent<Brand>() {
}