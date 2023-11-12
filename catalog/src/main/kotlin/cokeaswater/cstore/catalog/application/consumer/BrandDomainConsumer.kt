package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.BrandDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RemoveProductsByBrandCodeEvent
import cokeaswater.cstore.common.event.enums.DomainState
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
internal class BrandDomainConsumer(
    private val eventPublisher: ApplicationEventPublisher,
) {
    val log = KotlinLogging.logger { }


    @Async("domainQueExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun consumerGroupBrandDomain(ev: BrandDomainEvent) {
        log.info { "## Brand Domain Event Group : $ev" }

        val brand = ev.domain

        try {
            when (ev.state) {
                DomainState.DELETED -> eventPublisher.publishEvent(RemoveProductsByBrandCodeEvent(brand.code))
                else -> {}
            }
        } catch (e: Exception) {
            log.warn { "## Brand Domain Event Handle Failed : ${e.message}. ${ev.domain}" }
        }
    }
}