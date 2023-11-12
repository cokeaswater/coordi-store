package cokeaswater.cstore.api.presentation.brand

import cokeaswater.cstore.api.presentation.brand.dto.request.BrandModifyRequest
import cokeaswater.cstore.api.presentation.brand.dto.request.BrandRegisterRequest
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandSearchQuery
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandQueryCase
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("brands")
internal class BrandController(
    private val queryCase: BrandQueryCase,
    private val commandCase: BrandCommandCase,
    private val mapper: BrandToResponseMapper
) {


    @GetMapping
    @Operation(description = "브랜드 조회")
    fun searchProducts(
        @RequestParam(value = "brandCode") brandCode: String?,
        @PageableDefault(page = 0, size = 5) pageable: Pageable?
    ): ResponseEntity<Any> {
        val brands =
            queryCase.searchBrands(BrandSearchQuery(brandCode = brandCode, pageable = pageable ?: PageRequest.of(0, 5)))
        val resources = mapper.brandsToResponseResources(brands)
        return ResponseEntity.ok(resources)
    }


    @PostMapping
    @Operation(description = "브랜드 등록")
    fun registerProduct(@Valid @RequestBody req: BrandRegisterRequest): ResponseEntity<Any> {
        val brand = commandCase.registerBrand(
            BrandRegisterCommand(
                brandCode = req.code ?: "",
                name = req.name ?: ""
            )
        )

        return ResponseEntity(
            mapper.brandToResponseResource(brand), HttpStatus.CREATED
        )
    }

    @PatchMapping("{brandCode}")
    @Operation(description = "브랜드 수정")
    fun modifyProduct(
        @PathVariable("brandCode") brandCode: String,
        @Valid @RequestBody req: BrandModifyRequest
    ): ResponseEntity<Any> {

        val brand = commandCase.modifyBrand(
            BrandModifyCommand(
                code = brandCode,
                name = req.name,
                changeCode = req.changeCode
            )
        )
        return ResponseEntity(
            mapper.brandToResponseResource(brand), HttpStatus.OK
        )
    }

    @DeleteMapping("{brandCode}")
    @Operation(description = "브랜드 삭제")
    fun deleteProduct(
        @PathVariable("brandCode") brandCode: String
    ): ResponseEntity<Any> {

        commandCase.removeBrand(brandCode)
        return ResponseEntity.ok().build()
    }
}