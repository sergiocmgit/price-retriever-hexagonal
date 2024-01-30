package com.scosta.priceretrieverhexagonal.application.port.input

import com.scosta.priceretrieverhexagonal.application.domain.Price
import java.time.OffsetDateTime

interface GetPrice {
    operator fun invoke(input: GetPriceInput): Result<Price>
}

data class GetPriceInput(
    val productId: String,
    val brandId: Long,
    val appliedAt: OffsetDateTime
)
