package cokeaswater.cstore.catalog.domain

import com.github.f4b6a3.ulid.UlidCreator
import java.time.LocalDateTime

class Brand
internal constructor(
    val id: String,
    code: String,
    name: String,
    val registerAt: LocalDateTime,
    lastModifiedAt: LocalDateTime
) {
    var name: String = name
        private set

    var code: String = code
        private set

    var lastModifiedAt: LocalDateTime = lastModifiedAt
        private set


    internal fun changeName(name: String): Boolean {
        val isDiff = this.name != name
        if (isDiff) {
            this.name = name
            modified()
        }
        return isDiff
    }

    internal fun changeCode(targetCode: String): Boolean {
        val isDiff = this.code != targetCode
        if (isDiff) {
            this.code = targetCode
            modified()
        }
        return isDiff
    }

    private fun modified() {
        this.lastModifiedAt = LocalDateTime.now()
    }

    companion object Builder {
        internal fun generateNew(
            code: String,
            name: String,
        ): Brand {
            val now = LocalDateTime.now()
            return Brand(
                id = UlidCreator.getMonotonicUlid().toString(),
                code = code,
                name = name,
                registerAt = now,
                lastModifiedAt = now
            )
        }
    }
}