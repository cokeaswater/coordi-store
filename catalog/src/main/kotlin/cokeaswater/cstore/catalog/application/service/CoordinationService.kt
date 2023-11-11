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
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
internal class CoordinationService(
    private val persistencePort: CoordinationPersistencePort,
    private val eventPublisher: ApplicationEventPublisher
) : CoordinationQueryCase, CoordinationCommandCase {


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

    override fun querySummaryRecommendCoordination(): List<CoordinationProductDto> {
        val key = persistencePort.findLastCategoryCoordinationsPartitionKey() ?: run {
            // 브랜드부터 전체 재설정을 실시할지, 카테고리만 할지에 대한 판단은 고민해볼만한 사항
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
            return listOf()
        }
        return persistencePort.findLowestPriceCategoryCoordinationSet(key)
    }

    override fun queryBrandRecommendCoordination(): List<CoordinationProductDto> {
        val brandCoordinationScoreDto = persistencePort.findLowestPriceRecommendBrand() ?: run {
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
            return listOf()
        }

        val key =
            persistencePort.findLastBrandCategoryCoordinationsPartitionKey(brandCoordinationScoreDto.brandCode) ?: run {
                eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
                return listOf()
            }

        return persistencePort.findLowestPriceBrandCategoryCoordinationSet(
            brandCoordinationScoreDto.brandCode,
            key
        )
    }

    override fun queryCategoryMinMaxCoordination(category: ProductCategory): List<CoordinationProductDto> {
        val key: LocalDateTime = persistencePort.findLastCategoryCoordinationsPartitionKey() ?: run {
            eventPublisher.publishEvent(RefreshBrandCategoryCoordinationEvent())
            return listOf()
        }

        val list = persistencePort.findCategoryCoordinations(category, key)

        list
            .filter { e -> e.category == category }
            .sortedBy { e -> e.price }

        return when (list.size) {
            0 -> listOf()
            1 -> listOf(list.first())
            else -> listOf(list.first(), list.last())
        }
    }

}