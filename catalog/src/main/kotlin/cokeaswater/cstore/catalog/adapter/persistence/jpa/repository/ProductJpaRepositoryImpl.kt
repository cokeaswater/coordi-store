package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.QProductJpaEntity.productJpaEntity
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
}