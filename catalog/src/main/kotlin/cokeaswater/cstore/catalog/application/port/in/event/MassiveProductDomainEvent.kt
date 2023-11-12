package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.catalog.domain.Product
import cokeaswater.cstore.common.event.MassiveDomainEvent

class MassiveProductDomainEvent() : MassiveDomainEvent<Product>() {
}