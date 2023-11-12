package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.event.BrandDomainEvent
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandQueryCase
import cokeaswater.cstore.catalog.application.port.out.BrandPersistencePort
import cokeaswater.cstore.catalog.domain.Brand
import cokeaswater.cstore.common.event.enums.DomainState
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class BrandService(
    private val persistencePort: BrandPersistencePort,
    private val eventPublisher: ApplicationEventPublisher

) : BrandCommandCase, BrandQueryCase {

    private val log = KotlinLogging.logger { }

    override fun getBrandByCode(brandCode: String): Brand {
        val brand = checkNotNull(persistencePort.findBrandByCode(brandCode)) { "브랜드가 존재하지 않습니다. : $brandCode" }
        return brand

    }

    override fun searchBrands(query: BrandSearchQuery): List<Brand> {
        return persistencePort.searchBrands(query)
    }


    @Transactional
    override fun registerBrand(command: BrandRegisterCommand): Brand {

        val codeBrand = persistencePort.findBrandByCode(command.brandCode)

        if (codeBrand != null) throw IllegalStateException("이미 동일한 코드의 브랜드가 존재합니다.")

        val generated = Brand.generateNew(
            code = command.brandCode,
            name = command.name
        )

        persistencePort.saveBrand(generated)
        notifyBrandDomainEvent(generated, DomainState.CREATED)

        return generated;

    }

    @Transactional
    override fun modifyBrand(command: BrandModifyCommand): Brand {

        val targetBrand = checkNotNull(persistencePort.findBrandByCode(command.code)) { "대상 브랜드가 존재하지 않습니다." }

        var isModified = false

        if (!command.name.isNullOrBlank()) {
            isModified = targetBrand.changeName(command.name)
        }

        if (!command.changeCode.isNullOrBlank()) {
            val codeBrand = persistencePort.findBrandByCode(command.changeCode)
            if (codeBrand != null) throw IllegalStateException("이미 동일한 코드의 브랜드가 존재합니다.")

            isModified = targetBrand.changeCode(command.changeCode) || isModified
        }

        if (isModified) {
            persistencePort.saveBrand(targetBrand)
            notifyBrandDomainEvent(targetBrand, DomainState.UPDATED)
        }

        return targetBrand
    }

    @Transactional
    override fun removeBrand(brandCode: String) {
        val brand = persistencePort.findBrandByCode(brandCode) ?: return
        notifyBrandDomainEvent(brand, DomainState.DELETED)
        removeBrand(brand)
        brand.deleted()


    }

    private fun removeBrand(brand: Brand) {
        persistencePort.deleteBrand(brand)
    }

    private fun notifyBrandDomainEvent(brand: Brand, state: DomainState) {
        eventPublisher.publishEvent(BrandDomainEvent(domain = brand, state = state))
    }

}