package com.scosta.priceretrieverhexagonal.infrastructure.adapter

import com.scosta.priceretrieverhexagonal.application.port.output.FindPriceByProductIdAndBrandIdAtDate
import com.scosta.priceretrieverhexagonal.infrastructure.adapter.output.db.H2FindPriceByProductIdAndBrandIdAtDate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class InfrastructureConfiguration {

    @Bean
    fun findPriceByProductIdAndBrandIdAtDate(
        jdbcTemplate: JdbcTemplate
    ): FindPriceByProductIdAndBrandIdAtDate = H2FindPriceByProductIdAndBrandIdAtDate(jdbcTemplate)
}