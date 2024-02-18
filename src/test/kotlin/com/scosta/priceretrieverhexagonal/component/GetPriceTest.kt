package com.scosta.priceretrieverhexagonal.component

import com.scosta.priceretrieverhexagonal.utils.andContentAsDefinedInFile
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.test.web.servlet.get

class GetPriceTest : ComponentTest() {

    @TestFactory
    fun `should get prices`() = listOf(
        "2020-06-14T10:00:00Z" to "get_price_14th_at_10.json",
        "2020-06-14T16:00:00Z" to "get_price_14th_at_16.json",
        "2020-06-14T21:00:00Z" to "get_price_14th_at_21.json",
        "2020-06-15T10:00:00Z" to "get_price_15th_at_10.json",
        "2020-06-16T21:00:00Z" to "get_price_16th_at_21.json",
    ).map { (appliedAt, responseFile) ->
        dynamicTest(appliedAt) {
            // Given
            val productId = 35455
            val brandId = 1
            // When
            val result = mockMvc.get(
                "/api/prices?productId={productId}&brandId={brandId}&appliedAt={appliedAt}",
                productId,
                brandId,
                appliedAt
            )
            // Then
            result.andExpect {
                andContentAsDefinedInFile(responseFile)
            }
        }
    }
}