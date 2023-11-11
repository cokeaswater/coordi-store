package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.BrandJpaEntity
import cokeaswater.cstore.catalog.domain.Brand
import org.springframework.data.jpa.repository.JpaRepository

internal interface BrandJpaRepository : JpaRepository<BrandJpaEntity, String> {

    fun findByCode(code: String): BrandJpaEntity?

}