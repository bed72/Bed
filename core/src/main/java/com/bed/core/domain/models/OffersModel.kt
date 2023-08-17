package com.bed.core.domain.models

import java.util.Date

data class OffersModel(
    val id: String,
    val name: String,
    val price: Float,
    val description: String,
    val createdAt: Date,
    val deletedAt: Date
)
