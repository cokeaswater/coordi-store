package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandQueryCase
import cokeaswater.cstore.catalog.application.port.out.ProductPersistencePort
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.catalog.fixture.createBrandFixture
import cokeaswater.cstore.catalog.fixture.createProductFixture
import cokeaswater.cstore.catalog.fixture.random
import cokeaswater.cstore.common.domain.Money
import cokeaswater.cstore.common.event.DomainEvent
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockitoExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class ProductServiceTest {

    @Mock
    lateinit var brandQueryCase: BrandQueryCase

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @Mock
    lateinit var persistencePort: ProductPersistencePort

    @InjectMocks
    lateinit var service: ProductService


    @Test
    @Order(0)
    fun testSearchBrands(){

        val query = ProductSearchQuery(brandCode = "A")

        whenever(persistencePort.searchProducts(query)).thenReturn(listOf())

        val found = service.searchProduct(query)
        Assertions.assertEquals(0, found.size)

        Mockito.verify(persistencePort, Mockito.times(1)).searchProducts(query)
    }


    @Test
    @Order(1)
    @DisplayName("제품 등록 - 브랜드 없음")
    fun testRegisterProductNoBrand() {

        val brand = createBrandFixture();
        val cmd = ProductRegisterCommand(
            brandCode = brand.code,
            name = "AAA",
            category = ProductCategory.ACC,
            price = Money.of(1000)
        )

        whenever(brandQueryCase.getBrandByCode(brand.code)).thenReturn(null)

        Assertions.assertThrows(Exception::class.java) {
            service.registerProduct(cmd)
        }
    }

    @Test
    @Order(2)
    @DisplayName("제품 등록 정상")
    fun testRegisterProductNormal() {

        val brand = createBrandFixture();
        val cmd = ProductRegisterCommand(
            brandCode = brand.code,
            name = "AAA",
            category = ProductCategory.ACC,
            price = Money.of(1000)
        )

        whenever(brandQueryCase.getBrandByCode(brand.code)).thenReturn(brand)

        service.registerProduct(cmd)

        verify(eventPublisher, times(1)).publishEvent(anyVararg(DomainEvent::class))
        verify(persistencePort, times(1)).saveProduct(any())
    }


    @Test
    @Order(3)
    @DisplayName("제품 수정 - 제품 없음")
    fun testModifyProductNoProduct() {
        val product = createProductFixture()
        val cmd = ProductModifyCommand(
            productId = random.nextInt().toString(),
            brandCode = product.brandCode,
            name = "AAA",
            category = ProductCategory.ACC,
            price = Money.of(1000)
        )

        whenever(persistencePort.findProductById(product.brandCode)).thenReturn(null)

        Assertions.assertThrows(Exception::class.java) {
            service.modifyProduct(cmd)
        }
    }

    @Test
    @Order(4)
    @DisplayName("제품 수정 - 수정 브랜드 없음")
    fun testModifyProductNoTargetBrand() {
        val product = createProductFixture()
        val cmd = ProductModifyCommand(
            productId = random.nextInt().toString(),
            brandCode = product.brandCode,
            name = "AAA",
            category = ProductCategory.ACC,
            price = Money.of(1000)
        )

        whenever(persistencePort.findProductById(product.brandCode)).thenReturn(product)
        whenever(brandQueryCase.getBrandByCode(product.brandCode)).thenReturn(null)

        Assertions.assertThrows(Exception::class.java) {
            service.modifyProduct(cmd)
        }
    }

    @Test
    @Order(5)
    @DisplayName("제품 수정 - 브랜드 지정")
    fun testModifyProductTargetBrand() {
        val product = createProductFixture()
        val brand = createBrandFixture("HHH")
        val cmd = ProductModifyCommand(
            productId = product.id,
            brandCode = product.brandCode,
            name = "AAA",
            category = ProductCategory.ACC,
            price = Money.of(1000)
        )

        whenever(persistencePort.findProductById(product.id)).thenReturn(product)
        whenever(brandQueryCase.getBrandByCode(product.brandCode)).thenReturn(brand)

        service.modifyProduct(cmd)

        verify(eventPublisher, times(1)).publishEvent(anyVararg(DomainEvent::class))
        verify(persistencePort, times(1)).saveProduct(any())
    }


    @Test
    @DisplayName("제품 수정 - 나머지 수정")
    fun modifyProduct() {
        val product = createProductFixture()
        val brand = createBrandFixture(product.brandCode)
        val cmd = ProductModifyCommand(
            productId = product.id,
            brandCode = product.brandCode,
            name = "AAA",
            category = ProductCategory.ACC,
            price = Money.of(1000)
        )

        whenever(persistencePort.findProductById(product.id)).thenReturn(product)
        whenever(brandQueryCase.getBrandByCode(product.brandCode)).thenReturn(brand)

        service.modifyProduct(cmd)

        verify(eventPublisher, times(1)).publishEvent(anyVararg(DomainEvent::class))
        verify(persistencePort, times(1)).saveProduct(any())
    }

    @Test
    @DisplayName("제품 삭제")
    fun removeProduct() {
        val product = createProductFixture()
        whenever(persistencePort.findProductById(product.id)).thenReturn(product)


        service.removeProduct(product.id)

        verify(persistencePort, times(1)).deleteProduct(any())
    }
}