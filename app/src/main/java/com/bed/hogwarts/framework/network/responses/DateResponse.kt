package com.bed.hogwarts.framework.network.responses

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateResponse(
    @SerialName("seconds")
    val seconds: Long = 0,

    @SerialName("nanoseconds")
    val nanoseconds: Long = 0
) {
    companion object {
        fun empty() = DateResponse(0, 0)
    }
}

fun DateResponse.toLocalDate(): LocalDate {
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = (hours / 24).toInt()

    return LocalDate.fromEpochDays(days)
}
