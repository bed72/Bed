package com.bed.core.domain.alias

import arrow.core.Either

import com.bed.core.domain.models.MessageModel
import com.bed.core.domain.models.OffersModel

typealias GetOffersType = Either<MessageModel, List<OffersModel>>
