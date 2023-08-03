package com.bed.hogwarts.data.datasources.remote

import javax.inject.Inject

import io.ktor.http.HttpMethod
import io.ktor.client.request.url

import com.bed.hogwarts.framework.network.clients.HttpClient

import com.bed.core.domain.alias.GetProductDomainType
import com.bed.core.data.datasources.remote.ProductRemoteDatasource

import com.bed.hogwarts.framework.network.paths.ApiPath
import com.bed.hogwarts.framework.network.clients.request
import com.bed.hogwarts.framework.network.responses.message.toModel
import com.bed.hogwarts.framework.network.responses.product.toModel
import com.bed.hogwarts.framework.network.responses.message.MessageResponse
import com.bed.hogwarts.framework.network.responses.product.ProductResponse

class ProductRemoteDatasourceImpl @Inject constructor(
    private val client: HttpClient
) : ProductRemoteDatasource {
    override suspend fun getProduct(): GetProductDomainType =
        client.ktor.request<MessageResponse, List<ProductResponse>> {
            method = HttpMethod.Get
            url(ApiPath.GET_ALL_PRODUCTS.value)
        }
            .mapLeft { failure -> failure.toModel() }
            .map { success -> success.map { it.toModel() } }
}