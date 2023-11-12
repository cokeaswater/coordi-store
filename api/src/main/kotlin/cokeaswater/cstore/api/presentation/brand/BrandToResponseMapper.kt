package cokeaswater.cstore.api.presentation.brand

import cokeaswater.cstore.api.presentation.brand.dto.element.BrandResource
import cokeaswater.cstore.catalog.domain.Brand
import org.springframework.stereotype.Component

@Component
internal class BrandToResponseMapper {

    fun brandToResponseResource(brand: Brand): BrandResource {
        return BrandResource(
            id = brand.id,
            code = brand.code,
            name = brand.name,
            registerAt = brand.registerAt
        )
    }

    fun brandsToResponseResources(brands: List<Brand>): List<BrandResource> {
        return brands.map { e -> brandToResponseResource(e) }
    }
}