package com.scosta.priceretrieverhexagonal.application.usecase

import com.scosta.priceretrieverhexagonal.application.domain.model.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.domain.model.ProductId
import com.scosta.priceretrieverhexagonal.application.port.input.GetPrice
import com.scosta.priceretrieverhexagonal.application.port.input.GetPriceInput
import com.scosta.priceretrieverhexagonal.application.port.output.PriceRepository

class GetPriceUseCase(
    private val priceRepository: PriceRepository
) : GetPrice {

    override operator fun invoke(input: GetPriceInput): Result<Price> =
        priceRepository.find(ProductId(input.productId), BrandId(input.brandId), input.appliedAt)
}