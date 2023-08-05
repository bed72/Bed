package com.bed.hogwarts.framework.modules

import javax.inject.Singleton

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import com.bed.hogwarts.framework.network.clients.HttpClient
import com.bed.hogwarts.framework.network.clients.HttpClientImpl

@Module
@InstallIn(SingletonComponent::class)
interface ClientModule {

    @Binds
    @Singleton
    fun bindClient(client: HttpClientImpl): HttpClient

}
