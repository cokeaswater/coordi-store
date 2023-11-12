package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.BrandJpaEntity
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery

internal interface BrandJpaRepositoryCustom {

    fun searchBrands(query: BrandSearchQuery): List<BrandJpaEntity>
}