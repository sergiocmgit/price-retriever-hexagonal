package com.scosta.priceretrieverhexagonal.application.domain.model

import java.time.OffsetDateTime

data class Price(
    val id: Id,
    val productId: ProductId,
    val brandId: BrandId,
    val startAt: StartAt,
    val endAt: EndAt,
    val priceList: PriceList,
    val priority: Priority,
    val amount: Amount
) {
    @JvmInline
    value class Id(val value: Long)

    @JvmInline
    value class PriceList(val value: String)

    @JvmInline
    value class Priority(val value: Int)

    @JvmInline
    value class StartAt(val value: OffsetDateTime)

    @JvmInline
    value class EndAt(val value: OffsetDateTime)
}
