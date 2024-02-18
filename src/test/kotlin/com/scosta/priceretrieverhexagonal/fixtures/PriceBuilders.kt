package com.scosta.priceretrieverhexagonal.fixtures

import com.scosta.priceretrieverhexagonal.application.domain.Amount
import com.scosta.priceretrieverhexagonal.application.domain.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.Price
import com.scosta.priceretrieverhexagonal.application.domain.Price.EndAt
import com.scosta.priceretrieverhexagonal.application.domain.Price.Id
import com.scosta.priceretrieverhexagonal.application.domain.Price.PriceList
import com.scosta.priceretrieverhexagonal.application.domain.Price.Priority
import com.scosta.priceretrieverhexagonal.application.domain.Price.StartAt
import com.scosta.priceretrieverhexagonal.application.domain.ProductId
import com.scosta.priceretrieverhexagonal.application.port.input.GetPriceInput
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.time.OffsetDateTime
import java.util.Currency
import kotlin.random.Random

val DEFAULT_PRICE_ID = Random.nextLong()
const val DEFAULT_PRODUCT_ID = "some-product-id"
const val DEFAULT_BRAND_ID = 123L
val DEFAULT_START_AT: OffsetDateTime = OffsetDateTime.parse("2024-02-10T11:08:48Z")
val DEFAULT_END_AT: OffsetDateTime = DEFAULT_START_AT.plusHours(1)
val DEFAULT_APPLIED_AT: OffsetDateTime = DEFAULT_START_AT.plusMinutes(30)
const val DEFAULT_PRICE_LIST = "some-price-list"
const val DEFAULT_PRIORITY = 1
val DEFAULT_AMOUNT: BigDecimal = ONE.setScale(2)

fun buildPrice() = Price(
    id = Id(DEFAULT_PRICE_ID),
    productId = ProductId(DEFAULT_PRODUCT_ID),
    brandId = BrandId(DEFAULT_BRAND_ID),
    startAt = StartAt(DEFAULT_START_AT),
    endAt = EndAt(DEFAULT_END_AT),
    priceList = PriceList(DEFAULT_PRICE_LIST),
    priority = Priority(DEFAULT_PRIORITY),
    amount = Amount(DEFAULT_AMOUNT, Currency.getInstance("EUR")),
)

fun buildGetPriceInput() = GetPriceInput(
    DEFAULT_PRODUCT_ID,
    DEFAULT_BRAND_ID,
    DEFAULT_APPLIED_AT
)