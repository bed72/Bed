package com.bed.hogwarts.framework.network.responses

import androidx.annotation.StringRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.core.domain.models.MessageModel

@Serializable
data class MessageResponse(
    @SerialName("message")
    @StringRes val message: Int = 0
)

fun MessageResponse.toModel() = MessageModel(
    message
)
