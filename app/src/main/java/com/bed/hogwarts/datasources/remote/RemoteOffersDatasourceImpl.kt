package com.bed.hogwarts.datasources.remote

import arrow.core.left
import arrow.core.right

import javax.inject.Inject

import kotlinx.coroutines.tasks.await

import com.google.firebase.firestore.ktx.toObjects

import com.bed.hogwarts.framework.network.clients.HttpClient

import com.bed.core.domain.models.OffersModel
import com.bed.core.domain.models.MessageModel
import com.bed.core.domain.alias.GetOffersType

import com.bed.core.data.datasources.remote.RemoteOffersDatasource

import com.bed.hogwarts.framework.network.paths.Collections
import com.bed.hogwarts.framework.network.responses.toModel
import com.bed.hogwarts.framework.network.responses.OffersResponse
import com.bed.hogwarts.framework.network.responses.MessageResponse

class RemoteOffersDatasourceImpl @Inject constructor(
    private val client: HttpClient
) : RemoteOffersDatasource {
    override suspend fun getOffers(): GetOffersType {
        val response = client.firestore
            .collection(Collections.OFFERS.value)
            .get()
            .addOnSuccessListener { data ->
                data.toObjects<OffersResponse>()
            }
            .addOnCanceledListener {

            }
            .await()

        return if (response.isEmpty.not()) toSuccess(response.toObjects()).right()
        else toFailure(MessageResponse("Não conseguimos recuperar suas ofertas.")).left()
    }

    private fun toFailure(failure: MessageResponse): MessageModel = failure.toModel()

    private fun toSuccess(success: List<OffersResponse>): List<OffersModel> =
        success.map { it.toModel() }
}
