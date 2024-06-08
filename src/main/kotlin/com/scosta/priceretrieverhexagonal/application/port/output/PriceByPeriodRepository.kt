package com.scosta.priceretrieverhexagonal.application.port.output

import com.scosta.priceretrieverhexagonal.application.domain.model.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.domain.model.ProductId
import java.time.OffsetDateTime

interface FindPriceByProductIdAndBrandIdAtDate {
    operator fun invoke(productId: ProductId, brandId: BrandId, appliedAt: OffsetDateTime): Result<Price>
}