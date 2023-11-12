package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.out.BrandPersistencePort
import cokeaswater.cstore.catalog.fixture.createBrandFixture
import cokeaswater.cstore.catalog.fixture.createBrandRegisterCommandFixture
import cokeaswater.cstore.common.event.DomainEvent
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.whenever
import org.springframework.context.ApplicationEventPublisher

@DisplayName("브랜드 서비스 테스트")
@ExtendWith(MockitoExtension::class)
internal class BrandServiceTest {

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @Mock
    lateinit var persistencePort: BrandPersistencePort

    @InjectMocks
    lateinit var service: BrandService


    @Test
    fun testGetBrandByCode() {
        val fixture = createBrandFixture("001")

        whenever(persistencePort.findBrandByCode(fixture.code)).thenReturn(fixture)

        val found = service.getBrandByCode(fixture.code)
        assertTrue(found.code == fixture.code)

        verify(persistencePort, times(1)).findBrandByCode(anyString())
    }

    @Test
    fun testRegisterBrand() {
        val cmd = createBrandRegisterCommandFixture()

        service.registerBrand(cmd)

        verify(persistencePort, times(1)).saveBrand(any())
    }

    @Test
    fun modifyBrand() {

        val fixture = createBrandFixture("FFF")


        whenever(persistencePort.findBrandByCode(fixture.code)).thenReturn(fixture)
//        whenever(persistencePort.findBrandByCode(fixture.code)).thenReturn(fixture)

        var count: Int = 0;
        val empty = BrandModifyCommand(code = fixture.code)
        service.modifyBrand(empty)
        verify(persistencePort, times(count)).saveBrand(any())

        val nameNotChanged = BrandModifyCommand(code = fixture.code, name = fixture.name)
        service.modifyBrand(nameNotChanged)
        verify(persistencePort, times(count)).saveBrand(any())

        count += 1;
        val nameChanged = BrandModifyCommand(code = fixture.code, name = "AAA")
        service.modifyBrand(nameChanged)
        verify(persistencePort, times(count)).saveBrand(any())

        val codeNotChanged = BrandModifyCommand(code = fixture.code, changeCode = fixture.code)
        assertThrows(IllegalStateException::class.java) {
            service.modifyBrand(codeNotChanged)
        }

        count += 1;
        val codeChanged = BrandModifyCommand(code = fixture.code, changeCode = "ABCD")
        service.modifyBrand(codeChanged)
        verify(persistencePort, times(count)).saveBrand(any())
    }

    @Test
    fun removeBrand() {
        val fixture = createBrandFixture("001")

        whenever(persistencePort.findBrandByCode(fixture.code)).thenReturn(fixture)

        service.removeBrand(fixture.code)

        verify(persistencePort, times(1)).deleteBrand(any())
        verify(eventPublisher, times(1)).publishEvent(anyVararg(DomainEvent::class))

    }
}