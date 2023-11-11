package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.domain.Brand

interface BrandQueryCase {

    fun getBrandByCode(brandCode: String): Brand?
}