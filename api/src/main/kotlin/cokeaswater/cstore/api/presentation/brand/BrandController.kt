package cokeaswater.cstore.api.presentation.brand

import cokeaswater.cstore.api.presentation.brand.dto.request.BrandModifyRequest
import cokeaswater.cstore.api.presentation.brand.dto.request.BrandRegisterRequest
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.BrandRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.BrandCommandCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("brands")
internal class BrandController(
    private val commandCase: BrandCommandCase,
    private val mapper: BrandToResponseMapper
) {

    @PostMapping
    fun registerProduct(@Valid @RequestBody req: BrandRegisterRequest): ResponseEntity<Any> {
        val brand = commandCase.registerBrand(
            BrandRegisterCommand(
                brandCode = req.code ?: "",
                name = req.name ?: ""
            )
        )

        return ResponseEntity(
            mapper.brandToResponse(brand), HttpStatus.CREATED
        )
    }

    @PatchMapping("{brandCode}")
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
            mapper.brandToResponse(brand), HttpStatus.OK
        )
    }

    @DeleteMapping("{brandCode}")
    fun deleteProduct(
        @PathVariable("brandCode") brandCode: String
    ): ResponseEntity<Any> {

        commandCase.removeBrand(brandCode)
        return ResponseEntity.ok().build()
    }
}