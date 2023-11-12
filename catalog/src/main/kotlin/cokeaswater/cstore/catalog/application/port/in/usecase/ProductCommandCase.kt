package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.application.port.`in`.params.ProductModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductRegisterCommand
import cokeaswater.cstore.catalog.domain.Product

interface ProductCommandCase {

    fun registerProduct(command: ProductRegisterCommand): Product

    fun modifyProduct(command: ProductModifyCommand): Product

    fun removeProduct(productId: String)

    fun removeProductsByBrandCode(brandCode: String)


}