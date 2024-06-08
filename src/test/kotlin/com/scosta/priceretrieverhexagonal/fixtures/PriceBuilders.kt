package com.scosta.priceretrieverhexagonal.fixtures

import com.scosta.priceretrieverhexagonal.application.domain.model.Amount
import com.scosta.priceretrieverhexagonal.application.domain.model.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.EndAt
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.Id
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.PriceList
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.Priority
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.StartAt
import com.scosta.priceretrieverhexagonal.application.domain.model.ProductId
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

fun buildPrice(
    id: Long = DEFAULT_PRICE_ID,
    priority: Int = DEFAULT_PRIORITY
) = Price(
    id = Id(id),
    productId = ProductId(DEFAULT_PRODUCT_ID),
    brandId = BrandId(DEFAULT_BRAND_ID),
    startAt = StartAt(DEFAULT_START_AT),
    endAt = EndAt(DEFAULT_END_AT),
    priceList = PriceList(DEFAULT_PRICE_LIST),
    priority = Priority(priority),
    amount = Amount(DEFAULT_AMOUNT, Currency.getInstance("EUR")),
)

fun buildGetPriceInput() = GetPriceInput(DEFAULT_PRODUCT_ID, DEFAULT_BRAND_ID, DEFAULT_APPLIED_AT)