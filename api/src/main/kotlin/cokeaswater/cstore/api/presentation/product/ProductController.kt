package cokeaswater.cstore.api.presentation.product

import cokeaswater.cstore.api.presentation.product.dto.request.ProductModifyRequest
import cokeaswater.cstore.api.presentation.product.dto.request.ProductRegisterRequest
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductSearchQuery
import cokeaswater.cstore.catalog.application.port.`in`.usecase.ProductCommandCase
import cokeaswater.cstore.catalog.application.port.`in`.usecase.ProductQueryCase
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import cokeaswater.cstore.common.domain.Money
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("products")
internal class ProductController(
    private val queryCase: ProductQueryCase,
    private val commandCase: ProductCommandCase,
    private val mapper: ProductToResponseMapper
) {


    @GetMapping
    @Operation(description = "제품 조회")
    fun searchProducts(
        @RequestParam(value = "category") category: ProductCategory?,
        @RequestParam(value = "brandCode") brandCode: String?,
        @PageableDefault(page = 0, size = 5) pageable: Pageable?
    ): ResponseEntity<Any> {
        val products = queryCase.searchProduct(
            ProductSearchQuery(
                brandCode = brandCode,
                category = category,
                pageable = pageable ?: PageRequest.of(0, 5)
            )
        )
        val resources = mapper.productsToResponseResources(products)
        return ResponseEntity.ok(resources)
    }

    @PostMapping
    @Operation(description = "제품 등록")
    fun registerProduct(

        @Valid
        @RequestBody req: ProductRegisterRequest
    ): ResponseEntity<Any> {
        val product = commandCase.registerProduct(
            ProductRegisterCommand(
                category = req.category ?: ProductCategory.ACC,
                brandCode = req.brandCode ?: "",
                name = req.name ?: "",
                price = req.price ?: Money.of(1)
            )
        )

        return ResponseEntity(
            mapper.productToResponseResource(product), HttpStatus.CREATED
        )
    }

    @PatchMapping("{productId}")
    @Operation(description = "제품 수정")
    fun modifyProduct(
        @PathVariable("productId") productId: String,
        @Valid @RequestBody req: ProductModifyRequest
    ): ResponseEntity<Any> {

        val product = commandCase.modifyProduct(
            ProductModifyCommand(
                productId = productId,
                category = req.category,
                brandCode = req.brandCode,
                name = req.name,
                price = req.price
            )
        )
        return ResponseEntity(
            mapper.productToResponseResource(product), HttpStatus.OK
        )
    }

    @DeleteMapping("{productId}")
    @Operation(description = "제품 삭제")
    fun deleteProduct(
        @PathVariable("productId") productId: String
    ): ResponseEntity<Any> {

        commandCase.removeProduct(productId)
        return ResponseEntity.ok().build()
    }

}