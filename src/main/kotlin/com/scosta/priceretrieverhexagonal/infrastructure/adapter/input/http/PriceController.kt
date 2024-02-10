package com.scosta.priceretrieverhexagonal.infrastructure.adapter.input.http

import com.scosta.priceretrieverhexagonal.application.domain.Price
import com.scosta.priceretrieverhexagonal.application.port.input.GetPrice
import com.scosta.priceretrieverhexagonal.application.port.input.GetPriceInput
import java.math.BigDecimal
import java.time.OffsetDateTime
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/prices")
class PriceController(
    private val getPrice: GetPrice
) {

    @GetMapping
    fun getPrice(
        @RequestParam("productId") productId: String,
        @RequestParam("brandId") brandId: Long,
        @RequestParam("appliedAt") @DateTimeFormat(iso = DATE_TIME) appliedAt: OffsetDateTime,
    ): ResponseEntity<GetPriceResponse> =
        getPrice(GetPriceInput(productId, brandId, appliedAt))
            .getOrThrow()
            .toGetPriceResponse()
            .let { ResponseEntity.ok(it) }

    private fun Price.toGetPriceResponse() =
        GetPriceResponse(
            productId = productId.value,
            brandId = brandId.value,
            amount = amount.value,
            currency = amount.currency.displayName,
            priceList = priceList.value,
            startAt = startAt.value,
            endAt = endAt.value,
        )
}

data class GetPriceResponse(
    val productId: String,
    val brandId: Long,
    val amount: BigDecimal,
    val currency: String,
    val priceList: String,
    val startAt: OffsetDateTime,
    val endAt: OffsetDateTime,
)
