package cokeaswater.cstore.catalog.application.consumer

import cokeaswater.cstore.catalog.application.port.`in`.event.MassiveProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.RemoveProductsByBrandCodeEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.ProductCommandCase
import cokeaswater.cstore.catalog.fixture.createBrandDomainEvent
import cokeaswater.cstore.catalog.fixture.createProductDomainEvent
import cokeaswater.cstore.common.event.enums.DomainState
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

@DisplayName("브랜드 도메인 이벤트 컨슈머 테스트")
@ExtendWith(MockitoExtension::class)
internal class BrandDomainConsumerTest {

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher


    @InjectMocks
    lateinit var consumer: BrandDomainConsumer

    @Test
    @DisplayName("제품 단일 도메인 이벤트 핸들링")
    fun testConsumerGroupProductDomain(){

        val createdEv = createBrandDomainEvent()
        consumer.consumerGroupBrandDomain(createdEv)

        verify(eventPublisher, times(0)).publishEvent(anyVararg(RemoveProductsByBrandCodeEvent::class))

        val deletedEv = createBrandDomainEvent(DomainState.DELETED)
        consumer.consumerGroupBrandDomain(deletedEv)

        verify(eventPublisher, times(1)).publishEvent(anyVararg(RemoveProductsByBrandCodeEvent::class))
    }

}