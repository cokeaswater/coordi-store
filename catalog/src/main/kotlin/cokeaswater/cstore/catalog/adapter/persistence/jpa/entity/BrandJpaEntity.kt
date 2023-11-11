package cokeaswater.cstore.catalog.adapter.persistence.jpa.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "brand",
    indexes = [
        Index(name = "brand_c", columnList = "code", unique = true),
        Index(name = "brand_r", columnList = "register_at")
    ]
)
internal class BrandJpaEntity(
    @Id
    val id: String,

    @Column(name = "code")
    val code: String,

    val name: String,

    @Column(name = "register_at")
    val registerAt: LocalDateTime,

    @Column(name = "last_modified_at")
    val lastModifiedAt: LocalDateTime
) {
}