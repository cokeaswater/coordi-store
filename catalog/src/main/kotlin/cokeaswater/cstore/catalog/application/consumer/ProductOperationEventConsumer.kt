package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.RemoveProductsByBrandCodeEvent
import cokeaswater.cstore.catalog.application.port.`in`.usecase.ProductCommandCase
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
internal class ProductOperationEventConsumer(
    private val eventPublisher: ApplicationEventPublisher,
    private val commandCase: ProductCommandCase
) {

    val log = KotlinLogging.logger { }

    @Async("operationQueExecutor")
    @EventListener
    fun consumerGroupRefreshBrandCategoryCoordination(ev: RemoveProductsByBrandCodeEvent) {
        log.info { "## Remove Products By BrandCode Group : $ev" }
        try {
            commandCase.removeProductsByBrandCode(ev.brandCode)
        } catch (e: Exception) {
            log.info { "##  Remove Products By BrandCode Failed : ${e.message}" }
            if (ev.tried < 3) {
                ev.plusTryCount()
                eventPublisher.publishEvent(ev)
            }
        }
    }
}