package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.ProductJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


internal interface ProductJpaRepository : JpaRepository<ProductJpaEntity, String>, ProductJpaRepositoryCustom {

}