package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationScoreCommand
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestClassOrder
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@DisplayName("코디네이션 서비스 통합 테스트")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = OrderAnnotation::class)
internal class CoordinationServiceIntegrationTest(
    @Autowired private val service: CoordinationService
) {

    private val log = KotlinLogging.logger { }

    @BeforeAll
    fun init() {
        val summaryRefreshed = service.refreshSummaryCoordination()
        log.info { "## Refreshed : $summaryRefreshed" }
        assertEquals(0, summaryRefreshed)  // brand 기반 데이터가 있어야 재생성됨

        val scoreRefreshed = service.refreshBrandCoordinationScore(RefreshBrandCoordinationScoreCommand())
        log.info { "## Refreshed : $scoreRefreshed" }
        assertEquals(0, scoreRefreshed)  // brand 기반 데이터가 있어야 재생성됨

        val brandRefreshed = service.refreshBrandCoordination(RefreshBrandCoordinationCommand())
        log.info { "## Refreshed : $brandRefreshed" }
        assertEquals(72, brandRefreshed)

        service.refreshSummaryCoordination()
        service.refreshBrandCoordinationScore(RefreshBrandCoordinationScoreCommand())

    }

    @Test
    @Order(1)

    @DisplayName("종합 코디네이션 조회 테스트")
    fun testQueryCategoryRecommendCoordination() {

        val list = service.querySummaryRecommendCoordination()
        val totalPrice = list.sumOf { e -> e.price }
        log.info { "List Size : ${list.size} , List Total Price : $totalPrice" }
        assertEquals(8, list.size)
        assertEquals(34100, totalPrice)

    }


    @Test
    @Order(2)

    @DisplayName("브랜드 코디네이션 조회 테스트")
    fun testQueryBrandRecommendCoordination(){
        val list = service.queryBrandRecommendCoordination()
        val totalPrice = list.sumOf { e -> e.price }
        log.info { "List Size : ${list.size} , List Total Price : $totalPrice" }
        assertEquals(8, list.size)
        assertEquals(36100, totalPrice)


    }

    @Test
    @Order(3)

    @DisplayName("카테고리 코디네이션 조회 테스트")
    fun testQueryCategoryMinMaxCoordination(){
        val topList = service.queryCategoryMinMaxCoordination(ProductCategory.TOP).sortedBy { e -> e.price }
        val topPrice = topList.sumOf { e -> e.price }

        log.info { "List Size : ${topList.size} , List Total Price : $topPrice , List : $topList" }
        assertEquals(2, topList.size)
        assertEquals(10000, topList.first().price)
        assertEquals("C", topList.first().brandCode)
        assertEquals(11400, topList.last().price)
        assertEquals("I", topList.last().brandCode)
        assertEquals(21400, topPrice)

        val pantsList = service.queryCategoryMinMaxCoordination(ProductCategory.PANTS).sortedBy { e -> e.price }
        val pantsPrice = pantsList.sumOf { e -> e.price }
        log.info { "List Size : ${pantsList.size} , List Total Price : $pantsPrice" }
        assertEquals(2, pantsList.size)
        assertEquals(3000, pantsList.first().price)
        assertEquals("D", pantsList.first().brandCode)
        assertEquals(4200, pantsList.last().price)
        assertEquals("A", pantsList.last().brandCode)
        assertEquals(7200, pantsPrice)

        val hatList = service.queryCategoryMinMaxCoordination(ProductCategory.HAT).sortedBy { e -> e.price }
        val hatPrice = hatList.sumOf { e -> e.price }
        log.info { "List Size : ${hatList.size} , List Total Price : $hatPrice" }
        assertEquals(2, hatList.size)
        assertEquals(1500, hatList.first().price)
        assertEquals("D", hatList.first().brandCode)
        assertEquals(2000, hatList.last().price)
        assertEquals("B", hatList.last().brandCode)
        assertEquals(3500, hatPrice)
    }

}