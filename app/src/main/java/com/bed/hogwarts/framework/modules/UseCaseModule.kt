package com.bed.hogwarts.framework.modules

import javax.inject.Singleton

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.core.usecases.corroutine.CoroutinesUseCase
import com.bed.core.usecases.corroutine.CoroutinesUseCaseImpl

import com.bed.core.usecases.products.GetProductUseCase
import com.bed.core.usecases.products.GetProductUseCaseImp

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindCoroutineUseCase(useCase: CoroutinesUseCaseImpl): CoroutinesUseCase

    @Binds
    @Singleton
    fun bindGetProductUseCase(useCase: GetProductUseCaseImp): GetProductUseCase

}
