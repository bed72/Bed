package com.bed.core.usecases.products

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

import com.bed.core.usecases.UseCase
import com.bed.core.domain.alias.GetProductDomainType
import com.bed.core.data.repositories.ProductRepository
import com.bed.core.usecases.corroutine.CoroutinesUseCase


interface GetProductUseCase {
    operator fun invoke(parameters: Unit = Unit): Flow<GetProductDomainType>
}

class GetProductUseCaseImp @Inject constructor(
    private val useCase: CoroutinesUseCase,
    private val repository: ProductRepository
) : GetProductUseCase, UseCase<Unit, GetProductDomainType>() {
    override suspend fun doWork(parameters: Unit): GetProductDomainType =
        withContext(useCase.io()) { repository.getProduct() }
}