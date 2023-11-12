package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.MassiveProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RefreshBrandCategoryCoordinationEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationCommandCase
import cokeaswater.cstore.catalog.fixture.createProductDomainEvent
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.context.ApplicationEventPublisher

@DisplayName("제품 도메인 이벤트 컨슈머 테스트")
@ExtendWith(MockitoExtension::class)
internal class ProductDomainConsumerTest {

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @InjectMocks
    lateinit var consumer: ProductDomainConsumer

    @Test
    @DisplayName("제품 단일 도메인 이벤트 핸들링")
    fun testConsumerGroupProductDomain(){

        val ev = createProductDomainEvent()
        consumer.consumerGroupProductDomain(ev)

        verify(eventPublisher, times(1)).publishEvent(anyVararg(RefreshBrandCategoryCoordinationEvent::class))

    }

    @Test
    @DisplayName("제품 대량 도메인 이벤트 핸들링")
    fun testConsumerGroupMassiveProductDomain(){
        val ev = MassiveProductDomainEvent()
        consumer.consumerGroupMassiveProductDomain(ev)

        verify(eventPublisher, times(1)).publishEvent(anyVararg(RefreshBrandCategoryCoordinationEvent::class))
    }
}