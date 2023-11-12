package cokeaswater.cstore.common.event

import cokeaswater.cstore.common.event.enums.DomainState

abstract class DomainEvent<T> : ApplicationCustomEvent() {

    abstract val domain: T
    abstract val state: DomainState

}