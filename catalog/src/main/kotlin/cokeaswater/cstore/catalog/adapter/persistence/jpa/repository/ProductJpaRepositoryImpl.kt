package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.ProductJpaEntity
import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.QProductJpaEntity.productJpaEntity
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
internal class ProductJpaRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : ProductJpaRepositoryCustom {
    override fun deleteProductsByBrandCode(brandCode: String): Long {

        return queryFactory.delete(productJpaEntity)
            .where(productJpaEntity.brandCode.eq(brandCode))
            .execute()

    }

    override fun searchProducts(query: ProductSearchQuery): List<ProductJpaEntity> {
        return queryFactory.selectFrom(productJpaEntity)
            .where(eqCode(query.brandCode), eqCategory(query.category))
            .offset(query.pageable.offset)
            .limit(query.pageable.pageSize.toLong())
            .orderBy(productJpaEntity.registerAt.desc())
            .fetch()
    }

    private fun eqCode(brandCode: String?): BooleanExpression? {
        if (brandCode.isNullOrBlank()) return null
        return productJpaEntity.brandCode.eq(brandCode)
    }

    private fun eqCategory(category: ProductCategory?): BooleanExpression? {
        if (category == null) return null
        return productJpaEntity.category.eq(category)
    }
}