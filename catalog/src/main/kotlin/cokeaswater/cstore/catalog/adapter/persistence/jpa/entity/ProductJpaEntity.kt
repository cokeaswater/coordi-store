package cokeaswater.cstore.catalog.adapter.persistence.jpa.entity

import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "product",
    indexes = [
        Index(name = "product_b_c_p", columnList = "brand_code, category, price"),
        Index(name = "product_b_r", columnList = "brand_code, register_at"),
        Index(name = "product_c_r", columnList = "category, register_at")
    ]
)
class ProductJpaEntity(
    @Id
    val id: String,

    @Column(name = "brand_code")
    val brandCode: String,

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    val category: ProductCategory,

    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Int,

    @Column(name = "register_at")
    val registerAt: LocalDateTime,

    @Column(name = "last_modified_at")
    val lastModifiedAt: LocalDateTime,

    ) {

}