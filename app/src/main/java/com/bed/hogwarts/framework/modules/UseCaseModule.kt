package com.bed.hogwarts.framework.modules

import javax.inject.Singleton

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.core.usecases.corroutine.CoroutinesUseCase
import com.bed.core.usecases.corroutine.CoroutinesUseCaseImpl

import com.bed.core.usecases.sales.GetOffersUseCase
import com.bed.core.usecases.sales.GetOffersUseCaseImp

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindCoroutineUseCase(useCase: CoroutinesUseCaseImpl): CoroutinesUseCase

    @Binds
    @Singleton
    fun bindGetProductUseCase(useCase: GetOffersUseCaseImp): GetOffersUseCase

}
