package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

internal interface ProductJpaRepositoryCustom {

    fun deleteProductsByBrandCode(brandCode: String) : Long
}