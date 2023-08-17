package com.bed.hogwarts.framework.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.core.data.repositories.OffersRepository
import com.bed.core.data.repositories.OffersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindProductRepository(repository: OffersRepositoryImpl): OffersRepository

}
