package com.scosta.priceretrieverhexagonal.infrastructure.adapter.output.db

import com.scosta.priceretrieverhexagonal.application.domain.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.ProductId
import com.scosta.priceretrieverhexagonal.config.DatabaseInstance.jdbcTemplate
import com.scosta.priceretrieverhexagonal.config.DatabaseInstance.truncateAll
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_BRAND_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_PRODUCT_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_START_AT
import com.scosta.priceretrieverhexagonal.fixtures.buildPrice
import com.scosta.priceretrieverhexagonal.utils.DatabaseUtils.save
import kotlin.Result.Companion.success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class H2PriceRepositoryTest {

    private val repository = H2PriceRepository(jdbcTemplate)

    private val productId = ProductId(DEFAULT_PRODUCT_ID)
    private val brandId = BrandId(DEFAULT_BRAND_ID)
    private val appliedAt = DEFAULT_START_AT.plusSeconds(5)
    private val price = buildPrice()

    @BeforeAll
    fun insertPrice() {
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
}