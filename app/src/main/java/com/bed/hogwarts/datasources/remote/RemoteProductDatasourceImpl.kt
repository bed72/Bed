package com.bed.hogwarts.datasources.remote

import javax.inject.Inject

import io.ktor.http.HttpMethod
import io.ktor.client.request.url

import com.bed.hogwarts.framework.network.clients.HttpClient

import com.bed.core.domain.models.MessageModel
import com.bed.core.domain.models.ProductModel
import com.bed.core.domain.alias.GetProductDomainType

import com.bed.core.data.datasources.remote.RemoteProductDatasource

import com.bed.hogwarts.framework.network.paths.ApiPath
import com.bed.hogwarts.framework.network.clients.request
import com.bed.hogwarts.framework.network.responses.message.toModel
import com.bed.hogwarts.framework.network.responses.product.toModel
import com.bed.hogwarts.framework.network.responses.message.MessageResponse
import com.bed.hogwarts.framework.network.responses.product.ProductResponse

class RemoteProductDatasourceImpl @Inject constructor(
    private val client: HttpClient
) : RemoteProductDatasource {
    override suspend fun getProduct(): GetProductDomainType =
        client.ktor.request<MessageResponse, List<ProductResponse>> {
            method = HttpMethod.Get
            url(ApiPath.GET_ALL_PRODUCTS.value)
        }
            .map { toSuccess(it) }
            .mapLeft { toFailure(it) }

    private fun toFailure(failure: MessageResponse): MessageModel = failure.toModel()

    private fun toSuccess(success: List<ProductResponse>): List<ProductModel> =
        success.map { it.toModel() }
}
