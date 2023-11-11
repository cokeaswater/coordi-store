package cokeaswater.cstore.api.presentation.coordination

import cokeaswater.cstore.api.presentation.coordination.dto.element.CategorySetBase
import cokeaswater.cstore.api.presentation.coordination.dto.element.CoordinationLine
import cokeaswater.cstore.api.presentation.coordination.dto.response.CategorySetCoordinationResponse
import cokeaswater.cstore.api.presentation.coordination.dto.response.CoordinationLineSetResponse
import cokeaswater.cstore.catalog.application.port.`in`.dto.CoordinationProductDto
import cokeaswater.cstore.common.domain.Money
import org.springframework.stereotype.Component

@Component
internal class CoordinationDtoToResponseMapper {

    private fun checkValidListSizeForCategorySet(list: List<CoordinationProductDto>) {
        if (list.size != 8) throw IllegalStateException("제안될 데이터를 생성 중입니다.")
    }

    private fun convertCoordinationProductDtoToCoordinationLine(dto: CoordinationProductDto): CoordinationLine {
        return CoordinationLine(dto.category, Money.of(dto.price), dto.brandCode)
    }

    private fun convertListToCategorySetBaseList(list: List<CoordinationProductDto>): List<CoordinationLine> {
        val converted = list.sortedBy { e -> e.category.order }
            .map { e -> convertCoordinationProductDtoToCoordinationLine(e) }
        return converted
    }

    fun coordinationListToSummaryCoordination(list: List<CoordinationProductDto>): CategorySetCoordinationResponse {

        checkValidListSizeForCategorySet(list)

        val converted = convertListToCategorySetBaseList(list)

        val total = converted.sumOf { e -> e.price.value }

        return CategorySetCoordinationResponse(
            CategorySetBase(category = converted, total = Money.of(total))
        )

    }

    fun coordinationListToBrandCoordination(list: List<CoordinationProductDto>): CategorySetCoordinationResponse {

        checkValidListSizeForCategorySet(list)

        val converted = convertListToCategorySetBaseList(list)

        val total = converted.sumOf { e -> e.price.value }

        return CategorySetCoordinationResponse(
            CategorySetBase(
                brand = converted[0].brandCode,
                category = converted,
                total = Money.of(total)
            )
        )
    }

    fun coordinationListToMinMaxCoordination(list: List<CoordinationProductDto>): CoordinationLineSetResponse {
        if (list.isEmpty()) throw IllegalStateException("제안될 데이터를 생성 중입니다.")

        val converted = list.map { e -> convertCoordinationProductDtoToCoordinationLine(e) }

        return CoordinationLineSetResponse(
            lowest = listOf(converted.first()),
            highest = listOf(converted.last()),
            category = converted.first().category
        )
    }

}