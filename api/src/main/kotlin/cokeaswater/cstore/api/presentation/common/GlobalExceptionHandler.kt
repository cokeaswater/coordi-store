package cokeaswater.cstore.api.presentation.common

import mu.KotlinLogging
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler {

    private val log = KotlinLogging.logger { }

    @ExceptionHandler
    fun catchIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<*> {
        log.info { "## Catch IllegalArgument : ${e.message}" }

        return ResponseEntity(CustomBody(isSuccess = false, message = e.message), HttpStatus.BAD_REQUEST)

    }

    @ExceptionHandler
    fun catchIllegalArgumentException(e: IllegalStateException): ResponseEntity<*> {
        log.info { "## Catch IllegalStatus : ${e.message}" }

        return ResponseEntity(CustomBody(isSuccess = false, message = e.message), HttpStatus.BAD_REQUEST)

    }

    @ExceptionHandler
    fun catchRuntimeException(e: DataAccessException): ResponseEntity<*> {
        log.warn { "## Catch DataAccess : ${e.message}" }

        return ResponseEntity(
            CustomBody(isSuccess = false, message = "데이터 작업 중 오류가 발생했습니다."),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler
    fun catchRuntimeException(e: RuntimeException): ResponseEntity<*> {
        log.warn { "## Catch Unknown RuntimeException : ${e.message}" }

        return ResponseEntity(
            CustomBody(isSuccess = false, message = "서버 작업 중 오류가 발생했습니다."),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }


    @ExceptionHandler
    fun catchException(e: Exception): ResponseEntity<*> {
        log.error { "## Catch UnKnown Exception : ${e.message}" }

        return ResponseEntity(
            CustomBody(isSuccess = false, message = "서버 작업 중 오류가 발생했습니다."),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

}