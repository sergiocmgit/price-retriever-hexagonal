package com.scosta.priceretrieverhexagonal.infrastructure.adapter.output.db

import com.scosta.priceretrieverhexagonal.application.domain.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.Price
import com.scosta.priceretrieverhexagonal.application.domain.ProductId
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_BRAND_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_PRODUCT_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_START_AT
import com.scosta.priceretrieverhexagonal.fixtures.buildPrice
import kotlin.Result.Companion.success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate

// TODO: improve test, create some DatabaseUtils
@JdbcTest
@TestInstance(PER_CLASS)
class H2PriceRepositoryTest(
    @Autowired private val jdbcTemplate: JdbcTemplate
) {

    private val repository = H2PriceRepository(jdbcTemplate)

    private val productId = ProductId(DEFAULT_PRODUCT_ID)
    private val brandId = BrandId(DEFAULT_BRAND_ID)
    private val appliedAt = DEFAULT_START_AT.plusSeconds(5)
    private val price = buildPrice()

    @BeforeAll
    fun insertPrice() {
        // TODO: add truncate function
        save(price)
    }


    @Test
    fun `should find the price`() {
        // When
        val result = repository.find(productId, brandId, appliedAt)
        // Then
        assertThat(result).isEqualTo(success(price))
    }

    private fun save(price: Price) = with(price) {
        jdbcTemplate.update(
            """INSERT INTO price (id, product_id, brand_id, start_at, end_at, price_list, 
                priority, amount, currency) values 
                (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """,
            id.value,
            productId.value,
            brandId.value,
            startAt.value,
            endAt.value,
            priceList.value,
            priority.value,
            amount.value,
            amount.currency.currencyCode
        )
    }
}