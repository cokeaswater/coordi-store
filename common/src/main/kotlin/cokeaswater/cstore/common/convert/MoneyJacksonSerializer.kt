package cokeaswater.cstore.common.convert

import cokeaswater.cstore.common.domain.Money
import com.fasterxml.jackson.databind.util.StdConverter

class MoneyJacksonSerializer : StdConverter<Money, String>() {
    override fun convert(value: Money?): String {
        val v = value?.value ?: 0
        return String.format("%,d", v)
    }

}