package com.scosta.priceretrieverhexagonal.utils

import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.config.DatabaseInstance.jdbcTemplate

object DatabaseUtils {

    fun save(price: Price) = with(price) {
        jdbcTemplate.update(
            """INSERT INTO price (id, product_id, brand_id, start_at, end_at, price_list, 
                priority, amount, currency) values 
                (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """,
            id.value,
            productId.value,
            brandId.value,
            startAt.value,
            endAt.value,
            priceList.value,
            priority.value,
            amount.value,
            amount.currency.currencyCode
        )
    }
}