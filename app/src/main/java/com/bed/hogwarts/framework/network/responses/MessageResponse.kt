package com.bed.hogwarts.framework.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.core.domain.models.MessageModel

@Serializable
data class MessageResponse(
    @SerialName("message")
    val message: String = ""
)

fun MessageResponse.toModel() = MessageModel(
    message
)
