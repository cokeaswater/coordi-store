package cokeaswater.cstore.catalog.adapter.persistence.jpa.mapper

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.BrandJpaEntity
import cokeaswater.cstore.catalog.domain.Brand
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
internal class BrandCrossJpaEntityMapper {

    private val log = KotlinLogging.logger { }

    fun brandToEntity(brand: Brand): BrandJpaEntity {
        return BrandJpaEntity(
            id = brand.id,
            code = brand.code,
            name = brand.name,
            registerAt = brand.registerAt,
            lastModifiedAt = brand.lastModifiedAt
        )
    }

    fun entityToBrand(entity: BrandJpaEntity): Brand {
        return Brand(
            id = entity.id,
            code = entity.code,
            name = entity.name,
            registerAt = entity.registerAt,
            lastModifiedAt = entity.lastModifiedAt
        )
    }

}