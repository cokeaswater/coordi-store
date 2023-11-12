package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import cokeaswater.cstore.catalog.domain.Brand

interface BrandQueryCase {

    fun getBrandByCode(brandCode: String): Brand?

    fun searchBrands(query: BrandSearchQuery) : List<Brand>
}