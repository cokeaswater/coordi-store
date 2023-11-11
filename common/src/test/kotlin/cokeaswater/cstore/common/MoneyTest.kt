package cokeaswater.cstore.common

import cokeaswater.cstore.common.domain.Money
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class MoneyTest {

    private val log = KotlinLogging.logger { }

    @Test
    fun test() {
        val list = listOf(Money.of(1), Money.of(2), Money.of(1));
        val sorted = list.sortedBy { e -> e.value }
        log.info { "## Sorted : $sorted" }
    }
}