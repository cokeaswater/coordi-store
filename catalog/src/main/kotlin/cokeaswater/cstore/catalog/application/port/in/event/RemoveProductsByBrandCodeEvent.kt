package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.common.event.OperationEvent

data class RemoveProductsByBrandCodeEvent(
    val brandCode: String
) : OperationEvent() {
}