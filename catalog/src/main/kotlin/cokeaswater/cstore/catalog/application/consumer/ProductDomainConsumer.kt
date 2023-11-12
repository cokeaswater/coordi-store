package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.MassiveProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.ProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandCategoryCoordinationEvent
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
internal class ProductDomainConsumer(
    private val eventPublisher: ApplicationEventPublisher,
) {
    val log = KotlinLogging.logger { }


    @Async("domainQueExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun consumerGroupProductDomain(ev: ProductDomainEvent) {
        log.info { "## Product Domain Event Group : $ev" }

        val product = ev.domain

        try {
            eventPublisher.publishEvent(
                RefreshBrandCategoryCoordinationEvent(
                    brandCode = product.brandCode,
                    category = product.category,
                    price = product.price.value,
                    base = product.lastModifiedAt
                )
            )

        } catch (e: Exception) {
            log.warn { "## Product Domain Event Handle Failed : ${e.message} , ${ev.domain}" }
        }
    }

    @Async("domainQueExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun consumerGroupMassiveProductDomain(ev: MassiveProductDomainEvent) {
        log.info { "## Massive Product Domain Event Group : $ev" }

        try {
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
        } catch (e: Exception) {
            log.warn { "## Massive Product Domain Event Handle Failed : ${e.message} , $ev" }
        }
    }
}