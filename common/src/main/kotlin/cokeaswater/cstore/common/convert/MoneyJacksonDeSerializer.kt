package cokeaswater.cstore.common.convert

import cokeaswater.cstore.common.domain.Money
import com.fasterxml.jackson.databind.util.StdConverter

class MoneyJacksonDeSerializer : StdConverter<Any, Money>() {

    private val errorMessage = "가격 인식에 실패했습니다."

    private fun convert(value: String?): Money {
        val s = value ?: "0"
        val v = s.toInt()
        return Money.of(v)
    }

    private fun convert(value: Number?): Money {
        val v = value ?: 0
        return Money.of(v)
    }

    override fun convert(value: Any?): Money {
        return when (value) {
            is String -> convert(value)
            is Number -> convert(value)
            else -> throw IllegalArgumentException("$errorMessage : $value")
        }
    }
}