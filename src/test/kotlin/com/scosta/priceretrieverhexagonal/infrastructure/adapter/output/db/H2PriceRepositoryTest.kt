package com.scosta.priceretrieverhexagonal.infrastructure.adapter.output.db

import com.scosta.priceretrieverhexagonal.application.domain.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.Price
import com.scosta.priceretrieverhexagonal.application.domain.ProductId
import com.scosta.priceretrieverhexagonal.application.domain.exception.PriceNotFound
import com.scosta.priceretrieverhexagonal.config.DatabaseInstance.jdbcTemplate
import com.scosta.priceretrieverhexagonal.config.DatabaseInstance.truncateAll
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_BRAND_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_PRODUCT_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_START_AT
import com.scosta.priceretrieverhexagonal.fixtures.buildPrice
import com.scosta.priceretrieverhexagonal.utils.DatabaseUtils.save
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.random.Random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class H2PriceRepositoryTest {

    private val repository = H2PriceRepository(jdbcTemplate)

    private val productId = ProductId(DEFAULT_PRODUCT_ID)
    private val brandId = BrandId(DEFAULT_BRAND_ID)
    private val appliedAt = DEFAULT_START_AT.plusSeconds(5)
    private val price = buildPrice()

    @BeforeEach
    fun setup() {
        truncateAll()
        save(price)
    }


    @Test
    fun `should find the price`() {
        // When
        val result = repository.find(productId, brandId, appliedAt)
        // Then
        assertThat(result).isEqualTo(success(price))
    }


    @Test
    fun `should find the price with higher priority`() {
        // Given
        val priceWithHigherPriority = buildPrice(
            id = Random.nextLong(),
            priority = price.priority.value + 1
        ).also(::save)
        // When
        val result = repository.find(productId, brandId, appliedAt)
        // Then
        assertThat(result).isEqualTo(success(priceWithHigherPriority))
    }

    @TestFactory
    fun `should fail if the price cannot be found`() = listOf(
        "wrong productId" to Triple("other-product-id", DEFAULT_BRAND_ID, DEFAULT_START_AT.plusSeconds(5)),
        "wrong brandId" to Triple(DEFAULT_PRODUCT_ID, Random.nextLong(), DEFAULT_START_AT.plusSeconds(5)),
        "wrong appliedAt" to Triple(DEFAULT_PRODUCT_ID, DEFAULT_BRAND_ID, DEFAULT_START_AT.minusDays(1))
    ).map { (title, input) ->
        dynamicTest(title) {
            // When
            val result = repository.find(ProductId(input.first), BrandId(input.second), input.third)
            // Then
            assertThat(result).isEqualTo(failure<Price>(PriceNotFound))
        }
    }
}