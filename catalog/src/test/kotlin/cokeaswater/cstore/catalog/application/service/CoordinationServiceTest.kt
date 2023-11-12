package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.dto.BrandCoordinationScoreDto
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationScoreCommand
import cokeaswater.cstore.catalog.application.port.out.CoordinationPersistencePort
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.event.OperationEvent
import cokeaswater.cstore.common.exception.CustomRuntimeException
import mu.KotlinLogging
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.context.ApplicationEventPublisher
import java.time.LocalDateTime


@ExtendWith(MockitoExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("코디네이션 서비스 유닛 테스트")
internal class CoordinationServiceTest (
){

    private val log = KotlinLogging.logger { }


    @Mock
    lateinit var persistencePort: CoordinationPersistencePort

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @InjectMocks
    lateinit var service: CoordinationService


    @Test
    @Order(1)
    @DisplayName("카테고리 최고저가")
    fun testQueryCategoryMinMaxCoordination() {
        val key = LocalDateTime.now()
        whenever(persistencePort.findLastCategoryCoordinationsPartitionKey()).thenReturn(key)
        whenever(persistencePort.findCategoryCoordinations(any(), any())).thenReturn(listOf())

        val list = service.queryCategoryMinMaxCoordination(ProductCategory.ACC)

        assertEquals(0, list.size)

        verify(eventPublisher, times(0)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(1)).findCategoryCoordinations(ProductCategory.ACC, key)
    }

    @Test
    @Order(2)
    @DisplayName("카테고리 최고저가 - 기반 데이터 없음")
    fun testQueryCategoryMinMaxCoordinationNoData() {

        whenever(persistencePort.findLastCategoryCoordinationsPartitionKey()).thenReturn(null)


        assertThrows(CustomRuntimeException::class.java) {
            val list = service.queryCategoryMinMaxCoordination(ProductCategory.ACC)
            assertEquals(0, list.size)
        }


        verify(eventPublisher, times(1)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(0)).findCategoryCoordinations(any(), any())

    }

    @Test
    @Order(3)
    @DisplayName("브랜드 기준 추천")
    fun testQueryBrandRecommendCoordination() {

        val brandScore = BrandCoordinationScoreDto(
            brandCode = "AAA",
            sumOfCategoryPrice = 1,
            countOfCategory = 1,
            averagePrice = 1,
            scoreKey = LocalDateTime.now()
        )

        val key = LocalDateTime.now()

        whenever(persistencePort.findLowestPriceRecommendBrand()).thenReturn(brandScore)
        whenever(persistencePort.findLastBrandCategoryCoordinationsPartitionKey(any())).thenReturn(key)
        whenever(persistencePort.findLowestPriceBrandCategoryCoordinationSet(any(), any())).thenReturn(listOf())


        val list = service.queryBrandRecommendCoordination()

        assertEquals(0, list.size)

        verify(eventPublisher, times(0)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(1)).findLowestPriceBrandCategoryCoordinationSet(
            brandScore.brandCode,
            key
        )
    }

    @Test
    @Order(4)
    @DisplayName("브랜드 기준 추천 - 기반 데이터 없음")
    fun testQueryBrandRecommendCoordinationNoData() {

        whenever(persistencePort.findLowestPriceRecommendBrand()).thenReturn(null)

        assertThrows(CustomRuntimeException::class.java) {
            val list = service.queryBrandRecommendCoordination()
            assertEquals(0, list.size)
        }

        verify(eventPublisher, times(1)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(0)).findLowestPriceBrandCategoryCoordinationSet(any(), any())
    }

    @Test
    @Order(5)
    @DisplayName("카테고리 기준 추천 - 기반 데이터 없음")
    fun queryCategoryRecommendCoordinationNoData() {
        whenever(persistencePort.findLastCategoryCoordinationsPartitionKey()).thenReturn(null)

        assertThrows(CustomRuntimeException::class.java) {
            val list = service.querySummaryRecommendCoordination()
            assertEquals(0, list.size)
        }


        verify(eventPublisher, times(1)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(0)).findLowestPriceCategoryCoordinationSet(any())
    }

    @Test
    @Order(6)
    @DisplayName("카테고리 기준 추천")
    fun queryCategoryRecommendCoordination() {
        val key = LocalDateTime.now()
        whenever(persistencePort.findLastCategoryCoordinationsPartitionKey()).thenReturn(key)
        whenever(persistencePort.findLowestPriceCategoryCoordinationSet(key)).thenReturn(listOf())

        val list = service.querySummaryRecommendCoordination()

        assertEquals(0, list.size)

        verify(eventPublisher, times(0)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(1)).findLowestPriceCategoryCoordinationSet(key)

    }

    @Test
    @Order(7)
    @DisplayName("브랜드 기반 데이터 갱신 - 전체 요청")
    fun refreshBrandCoordinationAll() {
        val cmd = RefreshBrandCoordinationCommand()

        whenever(persistencePort.refreshBrandCoordinations(anyOrNull(), any())).thenReturn(0)

        service.refreshBrandCoordination(cmd)

        verify(persistencePort, times(1)).refreshBrandCoordinations(anyOrNull(), any())
        verify(eventPublisher, times(0)).publishEvent(anyVararg(OperationEvent::class))

        whenever(persistencePort.refreshBrandCoordinations(anyOrNull(), any())).thenReturn(1)

        service.refreshBrandCoordination(cmd)
        verify(persistencePort, times(2)).refreshBrandCoordinations(anyOrNull(), any())
        verify(eventPublisher, times(2)).publishEvent(anyVararg(OperationEvent::class))
    }

    @Test
    @Order(8)
    @DisplayName("브랜드 기반 데이터 갱신 - 브랜드 데이터 없음")
    fun refreshBrandCoordinationByBrandNoData() {
        val brandCode = "AA"
        val cmd = RefreshBrandCoordinationCommand(brandCode)

        whenever(persistencePort.findLastBrandCategoryCoordinationsPartitionKey(brandCode)).thenReturn(null)
        whenever(persistencePort.refreshBrandCoordinations(anyOrNull(), any())).thenReturn(2)

        service.refreshBrandCoordination(cmd)

        verify(persistencePort, times(1)).refreshBrandCoordinations(anyOrNull(), any())
        verify(eventPublisher, times(2)).publishEvent(anyVararg(OperationEvent::class))
    }

    @Test
    @Order(9)
    @DisplayName("브랜드 기반 데이터 갱신")
    fun refreshBrandCoordinationByBrandExistsData() {
        val brandCode = "AA"
        val oldBaseCmd = RefreshBrandCoordinationCommand(brandCode = brandCode, baseKey = LocalDateTime.MIN)
        val key = LocalDateTime.now()

        whenever(persistencePort.findLastBrandCategoryCoordinationsPartitionKey(brandCode)).thenReturn(key)
        whenever(persistencePort.refreshBrandCoordinations(anyOrNull(), any())).thenReturn(2)

        val r = service.refreshBrandCoordination(oldBaseCmd)

        assertEquals(0, r)

        verify(persistencePort, times(0)).refreshBrandCoordinations(anyOrNull(), any())

        val cmd = RefreshBrandCoordinationCommand(brandCode = brandCode, baseKey = LocalDateTime.MAX)

        service.refreshBrandCoordination(cmd)


        verify(eventPublisher, times(2)).publishEvent(anyVararg(OperationEvent::class))
        verify(persistencePort, times(1)).refreshBrandCoordinations(anyOrNull(), any())

    }


    @Test
    @Order(10)
    @DisplayName("카테고리 기반 데이터 갱신")
    fun refreshCategoryCoordinationBase() {

        service.refreshSummaryCoordination()

        verify(persistencePort, times(1)).refreshCategoryCoordinations(any())
    }


    @Test
    @Order(11)
    @DisplayName("브랜드 코디네이팅 순위 갱신")
    fun refreshBrandTotalCoordinationScore() {

        service.refreshBrandCoordinationScore(RefreshBrandCoordinationScoreCommand())

        verify(persistencePort, times(1)).refreshBrandTotalCoordinationScore(anyOrNull())
    }

}