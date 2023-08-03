package com.bed.hogwarts.framework.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.core.data.datasources.remote.ProductRemoteDatasource
import com.bed.hogwarts.data.datasources.remote.ProductRemoteDatasourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {

    @Binds
    fun bindProductDatasource(datasource: ProductRemoteDatasourceImpl): ProductRemoteDatasource

}