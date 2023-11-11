package cokeaswater.cstore.catalog.application.service

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandQueryCase
import cokeaswater.cstore.catalog.application.port.out.BrandPersistencePort
import cokeaswater.cstore.catalog.domain.Brand
import mu.KotlinLogging
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class BrandService(

    private val persistencePort: BrandPersistencePort

) : BrandCommandCase, BrandQueryCase {

    private val log = KotlinLogging.logger { }

    override fun getBrandByCode(brandCode: String): Brand {
        val brand = checkNotNull(persistencePort.findBrandByCode(brandCode)) { "브랜드가 존재하지 않습니다. : $brandCode" }
        return brand

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
            try {
                persistencePort.saveBrand(targetBrand);
            } catch (e: DataIntegrityViolationException) {
                throw IllegalStateException("동일 키의 데이터가 이미 존재합니다. : ${command.changeCode}")
            }
        }

        return targetBrand
    }

    @Transactional
    override fun removeBrand(brandCode: String) {
        val brand = persistencePort.findBrandByCode(brandCode) ?: return
        removeBrand(brand)
    }

    private fun removeBrand(brand: Brand) {
        persistencePort.deleteBrand(brand)
    }


}