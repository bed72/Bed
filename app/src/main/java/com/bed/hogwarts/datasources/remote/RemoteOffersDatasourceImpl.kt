package com.bed.hogwarts.datasources.remote

import arrow.core.left
import arrow.core.right

import javax.inject.Inject

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

import com.google.firebase.firestore.ktx.toObjects

import com.bed.core.domain.models.OffersModel
import com.bed.core.domain.models.MessageModel
import com.bed.core.domain.alias.GetOffersType

import com.bed.core.data.datasources.remote.RemoteOffersDatasource

import com.bed.hogwarts.framework.resources.StringResource
import com.bed.hogwarts.framework.network.paths.Collections
import com.bed.hogwarts.framework.network.responses.toModel
import com.bed.hogwarts.framework.network.clients.HttpClient
import com.bed.hogwarts.framework.network.responses.OffersResponse
import com.bed.hogwarts.framework.network.responses.MessageResponse

class RemoteOffersDatasourceImpl @Inject constructor(
    private val client: HttpClient
) : RemoteOffersDatasource {
    override suspend fun getOffers(): GetOffersType = suspendCoroutine { continuation ->
        client.firestore.collection(Collections.OFFERS.value).get()
            .addOnSuccessListener { collections ->
                continuation.resume(toSuccess(collections.toObjects()).right())
            }
            .addOnFailureListener {
                continuation.resume(
                    toFailure(MessageResponse(StringResource.GET_OFFERS_FAILURE.value)).left()
                )
            }
    }

    private fun toFailure(failure: MessageResponse): MessageModel = failure.toModel()

    private fun toSuccess(success: List<OffersResponse>): List<OffersModel> =
        success.map { it.toModel() }
}
