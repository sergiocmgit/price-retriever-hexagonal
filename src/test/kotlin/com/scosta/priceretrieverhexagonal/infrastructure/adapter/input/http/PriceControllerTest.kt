package com.scosta.priceretrieverhexagonal.infrastructure.adapter.input.http

import com.ninjasquad.springmockk.MockkBean
import com.scosta.priceretrieverhexagonal.application.port.input.GetPrice
import com.scosta.priceretrieverhexagonal.fixtures.buildGetPriceInput
import com.scosta.priceretrieverhexagonal.fixtures.buildPrice
import io.mockk.every
import kotlin.Result.Companion.success
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PriceController::class)
class PriceControllerTest {
    @MockkBean
    private lateinit var getPrice: GetPrice

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val input = buildGetPriceInput()
    private val price = buildPrice()

    @Test
    fun `should get a price`() {
        every { getPrice(input) } returns success(price)

        mockMvc.get(
            "/api/prices?productId={productId}&brandId={brandId}&appliedAt={appliedAt}",
            input.productId,
            input.brandId,
            input.appliedAt
        ).andExpect { status().isOk }.andExpect {
            content {
                json(
                    """{
                    "productId": "some-product-id",
                    "brandId": 123,
                    "amount": 1,
                    "currency": "EUR",
                    "priceList": "some-price-list",
                    "startAt": "2024-02-10T11:08:48.415252+01:00",
                    "endAt": "2024-02-10T12:08:48.415252+01:00"
                }""".trimIndent()
                )
            }
        }
    }
}