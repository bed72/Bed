package com.bed.hogwarts.framework.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.core.data.datasources.remote.RemoteOffersDatasource
import com.bed.hogwarts.datasources.remote.RemoteOffersDatasourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {

    @Binds
    fun bindProductDatasource(datasource: RemoteOffersDatasourceImpl): RemoteOffersDatasource

}
