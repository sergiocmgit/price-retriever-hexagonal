package com.scosta.priceretrieverhexagonal.application.usecase

import com.scosta.priceretrieverhexagonal.application.domain.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.ProductId
import com.scosta.priceretrieverhexagonal.application.port.input.GetPriceInput
import com.scosta.priceretrieverhexagonal.application.port.output.PriceRepository
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_BRAND_ID
import com.scosta.priceretrieverhexagonal.fixtures.DEFAULT_PRODUCT_ID
import com.scosta.priceretrieverhexagonal.fixtures.buildPrice
import io.mockk.every
import io.mockk.mockk
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GetPriceUseCaseTest {

    private val priceRepository = mockk<PriceRepository>()
    private val useCase = GetPriceUseCase(priceRepository)

    @Test
    fun `should get a price`() {
        // Given
        val productId = ProductId(DEFAULT_PRODUCT_ID)
        val brandId = BrandId(DEFAULT_BRAND_ID)
        val appliedAt = OffsetDateTime.now()
        val input = GetPriceInput(productId.value, brandId.value, appliedAt)
        val expected = Result.success(buildPrice())
        every { priceRepository.find(productId, brandId, appliedAt) } returns expected
        // When
        val result = useCase(input)
        // Then
        assertThat(result).isEqualTo(expected)
    }
}