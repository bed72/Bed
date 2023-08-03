package com.bed.core.domain.models

import kotlinx.datetime.LocalDate

data class ProductModel(
    val id: String,
    val createdAt: LocalDate,
    val name: String,
    val price: Float,
    val description: String,
    val validation: LocalDate,
    val userId: String,
    val imageId: String,
    val categoryId: String
)
