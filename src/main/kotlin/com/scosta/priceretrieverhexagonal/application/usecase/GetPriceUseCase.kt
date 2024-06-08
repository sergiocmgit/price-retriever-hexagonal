package com.scosta.priceretrieverhexagonal.application.usecase

import com.scosta.priceretrieverhexagonal.application.domain.model.BrandId
import com.scosta.priceretrieverhexagonal.application.domain.model.Price
import com.scosta.priceretrieverhexagonal.application.domain.model.ProductId
import com.scosta.priceretrieverhexagonal.application.port.input.GetPrice
import com.scosta.priceretrieverhexagonal.application.port.input.GetPriceInput
import com.scosta.priceretrieverhexagonal.application.port.output.FindPriceByProductIdAndBrandIdAtDate

class GetPriceUseCase(
    private val findPriceByProductIdAndBrandIdAtDate: FindPriceByProductIdAndBrandIdAtDate
) : GetPrice {

    override operator fun invoke(input: GetPriceInput): Result<Price> =
        findPriceByProductIdAndBrandIdAtDate(ProductId(input.productId), BrandId(input.brandId), input.appliedAt)
}