package com.scosta.priceretrieverhexagonal.application

import com.scosta.priceretrieverhexagonal.application.port.input.GetPrice
import com.scosta.priceretrieverhexagonal.application.port.output.PriceRepository
import com.scosta.priceretrieverhexagonal.application.usecase.GetPriceUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun getPrice(
        priceRepository: PriceRepository
    ): GetPrice = GetPriceUseCase(priceRepository)
}
