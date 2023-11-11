package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.ProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationCommandCase
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductDomainConsumer(
    private val eventPublisher: ApplicationEventPublisher,
    private val coordinationCommandCase: CoordinationCommandCase
) {
    val log = KotlinLogging.logger { }


    @Async("operationQueExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun consumerGroupProductDomain(ev: ProductDomainEvent) {
        log.info { "## Product Domain Event Group : $ev" }

        val product = ev.domain

        try {
            coordinationCommandCase.refreshBrandCoordination(
                RefreshBrandCoordinationCommand(
                    brandCode = product.brandCode,
                    category = product.category,
                    price = product.price.value,
                    baseKey = product.lastModifiedAt

                )
            )
        } catch (e: Exception) {
            log.info { "## Refresh Brand-Category Coordination Failed : ${e.message}" }
            if (ev.tried < 3) {
                ev.plusTryCount()
                eventPublisher.publishEvent(ev)
            }
        }
    }
}