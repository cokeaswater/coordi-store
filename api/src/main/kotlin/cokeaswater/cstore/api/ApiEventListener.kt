package cokeaswater.cstore.api

import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandCategoryCoordinationEvent
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApiEventListener(
    private val eventPublisher: ApplicationEventPublisher
) {

    @EventListener
    fun onApplicationStartedEvent(ev: ApplicationStartedEvent) {
        eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
    }
}