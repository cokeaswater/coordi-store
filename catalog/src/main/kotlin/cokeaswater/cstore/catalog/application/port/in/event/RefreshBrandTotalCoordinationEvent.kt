package cokeaswater.cstore.catalog.application.port.`in`.event

import cokeaswater.cstore.common.event.OperationEvent

data class RefreshBrandTotalCoordinationEvent(
    val brandCode: String?
) : OperationEvent() {
}