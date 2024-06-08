package com.scosta.priceretrieverhexagonal.infrastructure.adapter.output.db

import com.scosta.priceretrieverhexagonal.application.domain.model.Amount
import com.scosta.priceretrieverhexagonal.application.domain.model.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.EndAt
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.Id
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.PriceList
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.Priority
import com.scosta.priceretrieverhexagonal.application.domain.model.Price.StartAt
import com.scosta.priceretrieverhexagonal.application.domain.model.ProductId
import com.scosta.priceretrieverhexagonal.application.domain.exception.PriceNotFound
import com.scosta.priceretrieverhexagonal.application.port.output.PriceRepository
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.time.ZoneOffset.UTC
import java.util.Currency
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class H2PriceRepository(
    private val jdbcTemplate: JdbcTemplate
) : PriceRepository {
    override fun find(productId: ProductId, brandId: BrandId, appliedAt: OffsetDateTime): Result<Price> =
        try {
            jdbcTemplate.queryForObject(
                """SELECT id, product_id, brand_id, start_at, end_at,
                price_list, priority, amount, currency
                FROM price
                WHERE product_id=? and brand_id=? and ? between start_at and end_at
                ORDER by priority desc
                LIMIT 1
            """,
                productId.value,
                brandId.value,
                appliedAt
            ) { rs, _ -> Result.success(rs.toPrice()) }
        } catch (_: Exception) {
            Result.failure(PriceNotFound)
        }

    private fun ResultSet.toPrice(): Price =
        Price(
            id = Id(getLong("id")),
            productId = ProductId(getString("product_id")),
            brandId = BrandId(getLong("brand_id")),
            startAt = StartAt(getUTCOffsetDateTime("start_at")),
            endAt = EndAt(getUTCOffsetDateTime("end_at")),
            priceList = PriceList(getString("price_list")),
            priority = Priority(getInt("priority")),
            amount = Amount(
                value = getBigDecimal("amount"),
                currency = Currency.getInstance(getString("currency"))
            )
        )

    private fun ResultSet.getUTCOffsetDateTime(column: String): OffsetDateTime =
        getTimestamp(column).toInstant().atOffset(UTC)
}