package com.scosta.priceretrieverhexagonal.infrastructure.adapter.input.http

import com.scosta.priceretrieverhexagonal.application.domain.exception.DomainError
import com.scosta.priceretrieverhexagonal.application.domain.exception.PriceNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(PriceNotFound::class)
    fun handleNotFound(domainError: DomainError): ResponseEntity<String> =
        ResponseEntity(domainError.message, HttpStatus.NOT_FOUND)
}