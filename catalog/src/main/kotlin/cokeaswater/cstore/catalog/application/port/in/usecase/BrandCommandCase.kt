package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.domain.Brand

interface BrandCommandCase {

    fun registerBrand(command: BrandRegisterCommand): Brand

    fun modifyBrand(command: BrandModifyCommand): Brand

    fun removeBrand(brandCode: String)

}