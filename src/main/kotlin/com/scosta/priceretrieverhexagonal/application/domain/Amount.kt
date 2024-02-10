package com.scosta.priceretrieverhexagonal.application.domain

import java.math.BigDecimal
import java.util.Currency

// TODO: include currency within the value object
// TODO: stop using java.util.Currency
data class Amount(
    val value: BigDecimal,
    val currency: Currency
)