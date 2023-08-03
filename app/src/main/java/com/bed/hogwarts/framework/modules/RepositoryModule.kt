package com.bed.hogwarts.framework.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.core.data.repositories.ProductRepository
import com.bed.core.data.repositories.ProductRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindProductRepository(repository: ProductRepositoryImpl): ProductRepository

}