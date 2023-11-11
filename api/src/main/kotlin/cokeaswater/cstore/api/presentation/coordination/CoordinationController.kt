package cokeaswater.cstore.api.presentation.coordination

import cokeaswater.cstore.api.jsonview.CoordinationView
import cokeaswater.cstore.api.presentation.common.CustomBody
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationQueryCase
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("coordination")
internal class CoordinationController(
    private val queryCase: CoordinationQueryCase,
    private val mapper: CoordinationDtoToResponseMapper
) {

    @GetMapping
    @RequestMapping("summary")
    @JsonView(CoordinationView.AllInclude::class)
    fun summaryCoordination(): ResponseEntity<*> {
        val list = queryCase.querySummaryRecommendCoordination()

        val body = mapper.coordinationListToSummaryCoordination(list)

        return ResponseEntity(CustomBody(result = body), HttpStatus.OK)
    }

    @GetMapping
    @JsonView(CoordinationView.CategoryInclude::class)
    @RequestMapping("brand")
    fun brandCoordination(): ResponseEntity<*> {
        val list = queryCase.queryBrandRecommendCoordination()

        val body = mapper.coordinationListToBrandCoordination(list)

        return ResponseEntity(CustomBody(result = body), HttpStatus.OK)

    }

    @GetMapping
    @JsonView(CoordinationView.BrandInclude::class)
    @RequestMapping("minMax")
    fun categoryCoordination(@RequestParam("category") category: ProductCategory): ResponseEntity<*> {

        val list = queryCase.queryCategoryMinMaxCoordination(category)

        val body = mapper.coordinationListToMinMaxCoordination(list)

        return ResponseEntity(CustomBody(result = body), HttpStatus.OK)

    }
}


