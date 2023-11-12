package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.ProductJpaEntity
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery

internal interface ProductJpaRepositoryCustom {

    fun deleteProductsByBrandCode(brandCode: String) : Long

    fun searchProducts(query : ProductSearchQuery) : List<ProductJpaEntity>
}