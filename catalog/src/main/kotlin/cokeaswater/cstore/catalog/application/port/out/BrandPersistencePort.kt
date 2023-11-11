package cokeaswater.cstore.catalog.application.port.out

import cokeaswater.cstore.catalog.domain.Brand

internal interface BrandPersistencePort {

    fun findBrandByCode(brandCode: String): Brand?

    fun saveBrand(brand: Brand): Brand

    fun deleteBrand(brand: Brand)
}