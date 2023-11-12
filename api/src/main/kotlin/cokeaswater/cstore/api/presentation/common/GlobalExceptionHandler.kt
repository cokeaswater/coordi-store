package cokeaswater.cstore.api.presentation.common

import cokeaswater.cstore.common.exception.CustomRuntimeException
import mu.KotlinLogging
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    private val log = KotlinLogging.logger { }

    @ExceptionHandler(IllegalArgumentException::class)
    fun catchIllegalArgumentException(e: IllegalArgumentException, request: WebRequest): ResponseEntity<Any>? {
        log.info { "## Catch IllegalArgument : ${e.message}" }

        val status = HttpStatus.BAD_REQUEST
        val body = createProblemDetail(e, status, e.message ?: "요청 인자가 잘못되었습니다.", null, null, request)

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, status, request);

    }

    @ExceptionHandler(IllegalStateException::class)
    fun catchIllegalStateException(e: IllegalStateException, request: WebRequest): ResponseEntity<Any>? {
        log.info { "## Catch IllegalStatus : ${e.message}" }

        val status = HttpStatus.BAD_REQUEST

        val body = createProblemDetail(e, status, e.message ?: "적합한 상태가 아닙니다.", null, null, request)

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, status, request);

    }


    @ExceptionHandler(DataAccessException::class)
    fun catchDataAccessException(e: DataAccessException, request: WebRequest): ResponseEntity<Any>? {
        log.warn { "## Catch DataAccess : ${e.message}" }


        val status = HttpStatus.INTERNAL_SERVER_ERROR

        val body = createProblemDetail(e, status, "데이터 작업 중 오류가 발생했습니다.", null, null, request)

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, status, request);
    }

    @ExceptionHandler(CustomRuntimeException::class)
    fun catchCustomRuntimeException(e: CustomRuntimeException, request: WebRequest): ResponseEntity<Any>? {
        log.warn { "## Catch CustomRuntimeException : ${e.javaClass.name} , ${e.message}" }

        val status = HttpStatus.INTERNAL_SERVER_ERROR

        val body = createProblemDetail(e, status, e.message ?: "서버 내부 오류가 발생했습니다.", null, null, request)

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, status, request);

    }

    @ExceptionHandler(RuntimeException::class)
    fun catchRuntimeException(e: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        log.warn { "## Catch Unknown RuntimeException : ${e.javaClass.name} , ${e.message}" }

        val status = HttpStatus.INTERNAL_SERVER_ERROR

        val body = createProblemDetail(e, status, "서버 내부 오류가 발생했습니다.", null, null, request)

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, status, request);

    }


    @ExceptionHandler(Exception::class)
    fun catchException(e: Exception, request: WebRequest): ResponseEntity<Any>? {
        log.error { "## Catch UnKnown Exception : ${e.javaClass.name} , ${e.message}" }

        val status = HttpStatus.INTERNAL_SERVER_ERROR

        val body = createProblemDetail(e, status, "서버 내부 오류가 발생했습니다.", null, null, request)

        return handleExceptionInternal(e, body, HttpHeaders.EMPTY, status, request);

    }

}