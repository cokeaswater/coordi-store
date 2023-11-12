package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.event.MassiveProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.event.ProductDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandQueryCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.ProductCommandCase
import cokeaswater.cstore.catalog.application.port.out.ProductPersistencePort
import cokeaswater.cstore.catalog.domain.Brand
import cokeaswater.cstore.catalog.domain.Product
import cokeaswater.cstore.common.event.enums.DomainState
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@ConditionalOnBean(value = [CoordinationCommandCase::class])
internal class ProductService(
    private val brandQueryCase: BrandQueryCase,
    private val persistencePort: ProductPersistencePort,
    private val eventPublisher: ApplicationEventPublisher,
) : ProductCommandCase {

    private val log = KotlinLogging.logger { }

    @Transactional
    override fun registerProduct(command: ProductRegisterCommand): Product {

        val targetBrand: Brand =
            checkNotNull(brandQueryCase.getBrandByCode(command.brandCode)) { "매핑 브랜드가 존재하지 않습니다." };

        val generated = Product.generateNew(
            brand = targetBrand,
            category = command.category,
            name = command.name,
            price = command.price
        )

        notifyProductDomainEvent(generated, DomainState.CREATED)
        persistencePort.saveProduct(generated);

        return generated;
    }


    @Transactional
    override fun modifyProduct(command: ProductModifyCommand): Product {

        val product = checkNotNull(persistencePort.findProductById(command.productId)) { "대상 상품이 존재하지 않습니다." }

        var isModified = false

        if (!command.brandCode.isNullOrBlank()) {
            val brand = checkNotNull(brandQueryCase.getBrandByCode(command.brandCode)) { "매핑 브랜드가 존재하지 않습니다." }
            isModified = product.changeBrand(brand)
        }

        if (!command.name.isNullOrBlank()) {
            isModified = product.changeName(command.name) || isModified
        }

        if (command.category != null) {
            isModified = product.changeCategory(command.category) || isModified
        }

        var priceChanged = false

        if (command.price != null) {
            priceChanged = product.changePrice(command.price);
            isModified = priceChanged || isModified
        }

        if (isModified) {
            if (priceChanged) notifyProductDomainEvent(product, DomainState.UPDATED)
            persistencePort.saveProduct(product)
        }
        return product;
    }

    @Transactional
    override fun removeProduct(productId: String) {

        val product = persistencePort.findProductById(productId) ?: return;

        notifyProductDomainEvent(product, DomainState.DELETED)

        persistencePort.deleteProduct(product)
        product.deleted()
    }

    @Transactional
    override fun removeProductsByBrandCode(brandCode: String) {
        persistencePort.deleteProductByBrandCode(brandCode)
        eventPublisher.publishEvent(MassiveProductDomainEvent())
    }

    private fun notifyProductDomainEvent(product: Product, state: DomainState) {
        eventPublisher.publishEvent(ProductDomainEvent(domain = product, state = state))
    }

}