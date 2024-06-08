package com.scosta.priceretrieverhexagonal.application.usecase

import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.port.output.PriceRepository
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_APPLIED_AT
import com.scosta.priceretrieverhexagonal.fixtures.buildGetPriceInput
import com.scosta.priceretrieverhexagonal.fixtures.buildPrice
import io.mockk.every
import io.mockk.mockk
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GetPriceUseCaseTest {

    private val priceRepository = mockk<PriceRepository>()
    private val useCase = GetPriceUseCase(priceRepository)

    private val input = buildGetPriceInput()
    private val price = buildPrice()

    @Test
    fun `should get a price`() {
        // Given
        val expected = success(buildPrice())
        every { priceRepository.find(price.productId, price.brandId, DEFAULT_APPLIED_AT) } returns expected
        // When
        val result = useCase(input)
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return failure when the price cannot be found`() {
        // Given
        val expected = failure<Price>(Throwable("boom"))
        every { priceRepository.find(price.productId, price.brandId, DEFAULT_APPLIED_AT) } returns expected
        // When
        val result = useCase(input)
        // Then
        assertThat(result).isEqualTo(expected)
    }
}