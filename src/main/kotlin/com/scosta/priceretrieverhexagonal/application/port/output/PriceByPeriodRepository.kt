package com.scosta.priceretrieverhexagonal.application.port.output

import com.scosta.priceretrieverhexagonal.application.domain.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.Price
import com.scosta.priceretrieverhexagonal.application.domain.ProductId
import java.time.OffsetDateTime

interface PriceRepository {
    fun find(productId: ProductId, brandId: BrandId, appliedAt: OffsetDateTime): Result<Price>
}