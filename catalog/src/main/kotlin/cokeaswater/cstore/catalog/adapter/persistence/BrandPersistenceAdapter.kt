package cokeaswater.cstore.catalog.adapter.persistence

import cokeaswater.cstore.catalog.adapter.persistence.jpa.mapper.BrandCrossJpaEntityMapper
import cokeaswater.cstore.catalog.adapter.persistence.jpa.repository.BrandJpaRepository
import cokeaswater.cstore.catalog.application.port.out.BrandPersistencePort
import cokeaswater.cstore.catalog.domain.Brand
import org.springframework.stereotype.Component

@Component
internal class BrandPersistenceAdapter(
    private val repository: BrandJpaRepository,
    private val mapper: BrandCrossJpaEntityMapper
) : BrandPersistencePort {
    override fun findBrandByCode(brandCode: String): Brand? {
        val entity = repository.findByCode(brandCode) ?: return null
        return mapper.entityToBrand(entity)
    }

    override fun saveBrand(brand: Brand): Brand {
        val entity = mapper.brandToEntity(brand)
        val brand = mapper.entityToBrand(repository.save(entity))
        return brand
    }

    override fun deleteBrand(brand: Brand) {
        repository.deleteById(brand.id)
    }
}