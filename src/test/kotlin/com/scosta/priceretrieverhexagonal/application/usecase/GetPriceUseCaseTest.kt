package com.scosta.priceretrieverhexagonal.application.usecase

import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.port.output.FindPriceByProductIdAndBrandIdAtDate
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

    private val findPriceByProductIdAndBrandIdAtDate = mockk<FindPriceByProductIdAndBrandIdAtDate>()
    private val useCase = GetPriceUseCase(findPriceByProductIdAndBrandIdAtDate)

    private val input = buildGetPriceInput()
    private val price = buildPrice()

    @Test
    fun `should get a price`() {
        // Given
        val expected = success(buildPrice())
        every {
            findPriceByProductIdAndBrandIdAtDate(
                price.productId,
                price.brandId,
                DEFAULT_APPLIED_AT
            )
        } returns expected
        // When
        val result = useCase(input)
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return failure when the price cannot be found`() {
        // Given
        val expected = failure<Price>(Throwable("boom"))
        every {
            findPriceByProductIdAndBrandIdAtDate(
                price.productId,
                price.brandId,
                DEFAULT_APPLIED_AT
            )
        } returns expected
        // When
        val result = useCase(input)
        // Then
        assertThat(result).isEqualTo(expected)
    }
}