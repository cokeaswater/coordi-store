package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.domain.Product

interface ProductQueryCase {

    fun searchProduct(query : ProductSearchQuery) : List<Product>
}