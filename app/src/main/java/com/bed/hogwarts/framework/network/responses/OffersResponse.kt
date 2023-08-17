package com.bed.hogwarts.framework.network.responses

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

import com.bed.core.domain.models.OffersModel

@Serializable
data class OffersResponse(
    @SerialName("name")
    val name: String = "",

    @SerialName("price")
    val price: Float = 0F,

    @SerialName("description")
    val description: String = "",

    @Contextual
    @ServerTimestamp
    @SerialName("created_at")
    val createdAt: Timestamp = Timestamp.now(),

    @Contextual
    @ServerTimestamp
    @SerialName("deleted_at")
    val deletedAt: Timestamp = Timestamp.now()
)

fun OffersResponse.toModel() = OffersModel(
    id = "id",
    name = name,
    price = price,
    description = description,
    createdAt = createdAt.toDate(),
    deletedAt = deletedAt.toDate()
)
