package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.BrandJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface BrandJpaRepository : JpaRepository<BrandJpaEntity, String>, BrandJpaRepositoryCustom {

    fun findByCode(code: String): BrandJpaEntity?

}