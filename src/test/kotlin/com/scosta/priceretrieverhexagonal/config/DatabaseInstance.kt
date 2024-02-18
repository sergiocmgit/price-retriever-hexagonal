package com.scosta.priceretrieverhexagonal.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.jdbc.core.JdbcTemplate

const val PRICE_TABLE = "price"

object DatabaseInstance {

    val jdbcTemplate = HikariDataSource().apply {
        driverClassName = "org.h2.Driver"
        jdbcUrl = "jdbc:h2:file:./data/price-retriever-db"
        username = "name"
    }.let(::JdbcTemplate)

    private fun truncate(vararg tables: String) = jdbcTemplate.execute(
        """TRUNCATE TABLE ${tables.joinToString()}"""
    )

    fun truncateAll() = truncate(PRICE_TABLE)
}