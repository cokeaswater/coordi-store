package cokeaswater.cstore.common.domain

import cokeaswater.cstore.common.convert.MoneyJacksonDeSerializer
import cokeaswater.cstore.common.convert.MoneyJacksonSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize(converter = MoneyJacksonSerializer::class)
@JsonDeserialize(converter = MoneyJacksonDeSerializer::class)
class Money
private constructor(
    value: Number
) : Comparable<Money> {
    var value: Int = value.toInt()
        private set

    fun add(money: Money): Money {
        return Money(this.value + money.value)
    }


    override fun compareTo(other: Money): Int {
        return this.value.compareTo(other.value)
    }

    override fun toString(): String {
        return "Money(value=${value})"
    }

    companion object Builder {
        fun of(v: Number?): Money {
            return Money(v ?: 0)
        }
    }
}


