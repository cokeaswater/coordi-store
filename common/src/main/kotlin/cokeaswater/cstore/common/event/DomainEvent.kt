package cokeaswater.cstore.common.event

abstract class DomainEvent<T> : ApplicationCustomEvent() {

    abstract val domain: T

}