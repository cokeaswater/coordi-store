package cokeaswater.cstore.catalog.adapter.persistence.jpa.repository

import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.BrandJpaEntity
import cokeaswater.cstore.catalog.adapter.persistence.jpa.entity.QBrandJpaEntity.brandJpaEntity
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
internal class BrandJpaRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : BrandJpaRepositoryCustom {
    override fun searchBrands(query: BrandSearchQuery): List<BrandJpaEntity> {
        return queryFactory.selectFrom(brandJpaEntity)
            .where(eqCode(query.brandCode))
            .offset(query.pageable.offset)
            .limit(query.pageable.pageSize.toLong())
            .orderBy(brandJpaEntity.registerAt.desc())
            .fetch()
    }

    private fun eqCode(brandCode: String?): BooleanExpression? {
        if (brandCode.isNullOrBlank()) return null
        return brandJpaEntity.code.eq(brandCode)
    }
}