package cokeaswater.cstore.api.presentation.brand

import cokeaswater.cstore.api.presentation.brand.dto.response.BrandResourceResponse
import cokeaswater.cstore.catalog.domain.Brand
import org.springframework.stereotype.Component

@Component
internal class BrandToResponseMapper {

    fun brandToResponse(brand: Brand): BrandResourceResponse {
        return BrandResourceResponse(
            id = brand.id,
            code = brand.code,
            name = brand.name,
            registerAt = brand.registerAt
        )
    }
}