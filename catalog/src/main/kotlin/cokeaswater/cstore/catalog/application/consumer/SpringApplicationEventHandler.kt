package cokeaswater.cstore.catalog.application.consumer

import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class SpringApplicationEventHandler : ApplicationListener<ApplicationStartedEvent> {

    override fun onApplicationEvent(event: ApplicationStartedEvent) {

    }
}