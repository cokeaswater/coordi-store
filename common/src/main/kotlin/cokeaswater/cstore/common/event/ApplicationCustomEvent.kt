package cokeaswater.cstore.common.event

import java.time.LocalDateTime

abstract class ApplicationCustomEvent {

    val publishedAt: LocalDateTime = LocalDateTime.now()

    var tried: Int = 0
        private set

    fun plusTryCount() {
        this.tried += 1
    }
}