package com.scosta.priceretrieverhexagonal.integration

import java.io.File
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class GetPriceTest(
    @Autowired private val mockMvc: MockMvc
) {

    @TestFactory
    fun `should get prices`() = listOf(
        "2020-06-14T10:00:00Z" to "get_price_14th_at_10.json",
        "2020-06-14T16:00:00Z" to "get_price_14th_at_16.json",
        "2020-06-14T21:00:00Z" to "get_price_14th_at_21.json",
        "2020-06-15T10:00:00Z" to "get_price_15th_at_10.json",
        "2020-06-16T21:00:00Z" to "get_price_16th_at_21.json",
    ).map { (appliedAt, responseFile) ->
        dynamicTest(appliedAt) {
            val productId = 35455
            val brandId = 1

            val result = mockMvc.get(
                "/api/prices?productId={productId}&brandId={brandId}&appliedAt={appliedAt}",
                productId,
                brandId,
                appliedAt
            )

            result.andExpect {
                content {
                    json(responseFile.toJson())
                }
            }
        }
    }

    private fun String.toJson(): String =
        File("src/test/resources/integration-responses/$this").readText()
}