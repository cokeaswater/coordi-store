package cokeaswater.cstore.api.presentation.product

import cokeaswater.cstore.api.presentation.common.CustomBody
import cokeaswater.cstore.api.presentation.product.dto.request.ProductModifyRequest
import cokeaswater.cstore.api.presentation.product.dto.request.ProductRegisterRequest
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductModifyCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.ProductRegisterCommand
import cokeaswater.cstore.catalog.application.port.`in`.usecase.ProductCommandCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
internal class ProductController(
    private val commandCase: ProductCommandCase,
    private val mapper: ProductToResponseMapper
) {

    @PostMapping
    fun registerProduct(@RequestBody req: ProductRegisterRequest): ResponseEntity<*> {
        val product = commandCase.registerProduct(
            ProductRegisterCommand(
                category = req.category,
                brandCode = req.brandCode,
                name = req.name,
                price = req.price
            )
        )

        return ResponseEntity(
            CustomBody(result = mapper.productToResponse(product)), HttpStatus.CREATED
        )
    }

    @PatchMapping("{productId}")
    fun modifyProduct(
        @PathVariable("productId") productId: String,
        @RequestBody req: ProductModifyRequest
    ): ResponseEntity<*> {

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
            CustomBody(result = mapper.productToResponse(product)), HttpStatus.OK
        )
    }

}