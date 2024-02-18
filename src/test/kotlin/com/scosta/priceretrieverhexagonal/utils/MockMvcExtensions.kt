package com.scosta.priceretrieverhexagonal.utils

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import org.junit.jupiter.api.fail
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl

fun MockMvcResultMatchersDsl.andContentAsDefinedInFile(fileName: String, strictJsonMatching: Boolean = true) =
    andContentAsDefinedInString(
        InputStreamReader(
            FileInputStream(
                File(
                    javaClass.getResource("/rest/expected_result/$fileName")?.toURI()
                        ?: fail("could not load $fileName")
                )
            ),
            StandardCharsets.UTF_8
        ).readText().trimIndent(),
        strictJsonMatching
    )

fun MockMvcResultMatchersDsl.andContentAsDefinedInString(jsonContent: String, strictJsonMatching: Boolean = true) {
    content {
        json(
            jsonContent,
            strictJsonMatching
        )
    }
}