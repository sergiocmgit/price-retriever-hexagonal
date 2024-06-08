package com.scosta.priceretrieverhexagonal.application.domain.model

import java.math.BigDecimal
import java.util.Currency

data class Amount(
    val value: BigDecimal,
    val currency: Currency
)