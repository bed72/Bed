package com.bed.core.usecases.sales

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import com.bed.core.domain.alias.GetOffersType

import com.bed.core.data.repositories.OffersRepository

import com.bed.core.usecases.UseCaseWithoutParameter
import com.bed.core.usecases.corroutine.CoroutinesUseCase

interface GetOffersUseCase {
    operator fun invoke(): Flow<GetOffersType>
}

class GetOffersUseCaseImp @Inject constructor(
    private val useCase: CoroutinesUseCase,
    private val repository: OffersRepository
) : GetOffersUseCase, UseCaseWithoutParameter<GetOffersType>() {
    override suspend fun doWork(): GetOffersType =
        withContext(useCase.io()) { repository.getProduct() }
}
