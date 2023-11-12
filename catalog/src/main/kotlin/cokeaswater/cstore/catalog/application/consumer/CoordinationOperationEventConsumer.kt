package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandCategoryCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandTotalCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshCategoryCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationScoreCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationCommandCase
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.lang.RuntimeException


@Component
internal class CoordinationOperationEventConsumer(
    private val eventPublisher: ApplicationEventPublisher,
    private val coordinationCommandCase: CoordinationCommandCase
) {

    val log = KotlinLogging.logger { }


    @Async("operationQueExecutor")
    @EventListener
    fun consumerGroupRefreshBrandCategoryCoordination(ev: RefreshBrandCategoryCoordinationEvent) {
        log.info { "## Refresh Brand-Category Coordination Group : $ev" }

        try {
            coordinationCommandCase.refreshBrandCoordination(
                RefreshBrandCoordinationCommand(
                    brandCode = ev.brandCode,
                    category = ev.category,
                    price = ev.price,
                    baseKey = ev.base
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

    @Async("operationQueExecutor")
    @EventListener
    fun consumerGroupRefreshCategoryCoordination(ev: RefreshCategoryCoordinationEvent) {
        log.info { "## Refresh Category Coordination Group : $ev" }

        try {
            coordinationCommandCase.refreshSummaryCoordination()
        } catch (e: Exception) {
            log.info { "## Refresh Category Coordination Failed : ${e.message}" }
            if (ev.tried < 3) {
                ev.plusTryCount()
                eventPublisher.publishEvent(ev)
            }
        }
    }

    @Async("operationQueExecutor")
    @EventListener
    fun consumerGroupRefreshBrandTotalCoordination(ev: RefreshBrandTotalCoordinationEvent) {
        log.info { "## Refresh Brand-Total Coordination Group : $ev" }

        try {
            coordinationCommandCase.refreshBrandCoordinationScore(
                RefreshBrandCoordinationScoreCommand(ev.brandCode)
            )
        } catch (e: Exception) {
            log.info { "## Refresh Brand-Total Coordination Failed : ${e.message}" }
            if (ev.tried < 3) {
                ev.plusTryCount()
                eventPublisher.publishEvent(ev)
            }
        }
    }
}