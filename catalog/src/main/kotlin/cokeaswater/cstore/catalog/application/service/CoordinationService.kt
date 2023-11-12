package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.dto.CoordinationProductDto
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandCategoryCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandTotalCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshCategoryCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationScoreCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationQueryCase
import cokeaswater.cstore.catalog.application.port.out.CoordinationPersistencePort
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.exception.CustomRuntimeException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
internal class CoordinationService(
    private val persistencePort: CoordinationPersistencePort,
    private val eventPublisher: ApplicationEventPublisher
) : CoordinationQueryCase, CoordinationCommandCase {
    private val defaultErrorMessage = "추천용 데이터가 준비되지 않았습니다."
    private val log = KotlinLogging.logger { }

    @Transactional
    override fun refreshSummaryCoordination(): Int {
        val now = LocalDateTime.now()
        return persistencePort.refreshCategoryCoordinations(now)
    }

    @Transactional
    override fun refreshBrandCoordination(command: RefreshBrandCoordinationCommand): Int {

        val now = command.baseKey

        // 전체 재설정 요청 시 (brandCode = null)
        val brandCode = command.brandCode ?: run {
            return internalRefreshBrandCoordination(key = now)
        }

        // 요청 브랜드의 이전 데이터가 없을 시
        val key: LocalDateTime = persistencePort
            .findLastBrandCategoryCoordinationsPartitionKey(command.brandCode) ?: run {
            return internalRefreshBrandCoordination(command.brandCode, now)
        }

        if (key >= command.baseKey) return 0

        // TODO 요청 브랜드의 이전 데이터가 있다면, 값의 비교 후 재설정 판단 보완 구조 필요

        return internalRefreshBrandCoordination(brandCode, command.baseKey) // 0
    }

    private fun internalRefreshBrandCoordination(brandCode: String? = null, key: LocalDateTime): Int {
        val refreshed = persistencePort.refreshBrandCoordinations(brandCode, key)
        if (refreshed > 0) {
            eventPublisher.publishEvent(RefreshCategoryCoordinationEvent())
            eventPublisher.publishEvent(RefreshBrandTotalCoordinationEvent(brandCode))
        }
        return refreshed
    }


    @Transactional
    override fun refreshBrandCoordinationScore(command: RefreshBrandCoordinationScoreCommand): Int {
        return persistencePort.refreshBrandTotalCoordinationScore(command.brandCode)
    }

    @CircuitBreaker(name = "querySummaryRecommendCoordination", fallbackMethod = "circuitBreakingEmptyFallback")
    override fun querySummaryRecommendCoordination(): List<CoordinationProductDto> {
        val key = persistencePort.findLastCategoryCoordinationsPartitionKey() ?: run {
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
            throw CustomRuntimeException(defaultErrorMessage)
        }
        return persistencePort.findLowestPriceCategoryCoordinationSet(key)
    }

    @CircuitBreaker(name = "queryBrandRecommendCoordination", fallbackMethod = "circuitBreakingEmptyFallback")
    override fun queryBrandRecommendCoordination(): List<CoordinationProductDto> {

        val brandCoordinationScoreDto = persistencePort.findLowestPriceRecommendBrand() ?: run {
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
            throw CustomRuntimeException(defaultErrorMessage)
        }

        val key =
            persistencePort.findLastBrandCategoryCoordinationsPartitionKey(brandCoordinationScoreDto.brandCode) ?: run {
                eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
                throw CustomRuntimeException(defaultErrorMessage)
            }

        return persistencePort.findLowestPriceBrandCategoryCoordinationSet(
            brandCoordinationScoreDto.brandCode,
            key
        )
    }

    @CircuitBreaker(name = "queryCategoryMinMaxCoordination", fallbackMethod = "circuitBreakingEmptyFallback")
    override fun queryCategoryMinMaxCoordination(category: ProductCategory): List<CoordinationProductDto> {
        val key: LocalDateTime = persistencePort.findLastCategoryCoordinationsPartitionKey() ?: run {
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
            throw CustomRuntimeException(defaultErrorMessage)
        }

        val list = persistencePort.findCategoryCoordinations(category, key)
            .filter { e -> e.category == category }
            .sortedBy { e -> e.price }

        return when (list.size) {
            0 -> listOf()
            1 -> listOf(list.first())
            else -> listOf(list.first(), list.last())
        }
    }

    private fun circuitBreakingEmptyFallback(throwable: Throwable): List<CoordinationProductDto> {
        log.warn { "## CircuitBreaker Fallback Method Called, $throwable" }
        return listOf()
    }

}