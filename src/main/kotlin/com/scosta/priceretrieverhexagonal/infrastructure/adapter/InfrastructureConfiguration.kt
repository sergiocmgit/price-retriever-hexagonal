package com.scosta.priceretrieverhexagonal.infrastructure.adapter

import com.scosta.priceretrieverhexagonal.application.port.output.PriceRepository
import com.scosta.priceretrieverhexagonal.infrastructure.adapter.output.db.H2PriceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class InfrastructureConfiguration {

    @Bean
    fun priceRepository(
        jdbcTemplate: JdbcTemplate
    ): PriceRepository = H2PriceRepository(jdbcTemplate)
}