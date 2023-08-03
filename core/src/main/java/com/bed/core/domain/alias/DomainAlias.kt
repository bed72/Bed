package com.bed.core.domain.alias

import arrow.core.Either

import com.bed.core.domain.models.MessageModel
import com.bed.core.domain.models.ProductModel

typealias GetProductDomainType = Either<MessageModel, List<ProductModel>>