package com.scosta.priceretrieverhexagonal.application.domain.exception

sealed class DomainError : Exception() {
    abstract override val message: String
}

data object PriceNotFound : DomainError() {
    override val message: String = "PAYMENT_NOT_FOUND"
}