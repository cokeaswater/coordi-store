package cokeaswater.cstore.api.presentation.coordination

import cokeaswater.cstore.api.jsonview.CoordinationView
import cokeaswater.cstore.catalog.application.port.`in`.usecase.CoordinationQueryCase
import cokeaswater.cstore.catalog.domain.enums.ProductCategory
import com.fasterxml.jackson.annotation.JsonView
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
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

    @GetMapping("summary")
    @Operation(description = "모든 브랜드 제품 기준 카테고리 세트 가격 최저가 제품 조회")
    @JsonView(CoordinationView.AllInclude::class)
    fun summaryCoordination(): ResponseEntity<*> {
        val list = queryCase.querySummaryRecommendCoordination()

        val body = mapper.coordinationListToSummaryCoordination(list)

        return ResponseEntity(body, HttpStatus.OK)
    }

    @GetMapping("brand")
    @Operation(description = "동일 브랜드 카테고리 세트 가격 기준 최저가 브랜드 조회")
    @JsonView(CoordinationView.CategoryInclude::class)
    fun brandCoordination(): ResponseEntity<*> {
        val list = queryCase.queryBrandRecommendCoordination()

        val body = mapper.coordinationListToBrandCoordination(list)

        return ResponseEntity(body, HttpStatus.OK)

    }

    @GetMapping("minMax")
    @Operation(description = "카테고리 내 최고 / 최저 제품 조회")
    @JsonView(CoordinationView.BrandInclude::class)
    fun categoryCoordination(
        @RequestParam("category")
        @Schema(description = "카테고리 한글명 or ENUM name", required = true, type = "string", example = "상의", examples = ["상의","아우터", "바지", "스니커즈", "가방", "모자", "양말", "액세서리"])
        category: ProductCategory
    ): ResponseEntity<*> {

        val list = queryCase.queryCategoryMinMaxCoordination(category)

        val body = mapper.coordinationListToMinMaxCoordination(list)

        return ResponseEntity(body, HttpStatus.OK)

    }
}


