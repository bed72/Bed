package com.bed.hogwarts.framework.network.responses.product

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.bed.core.domain.models.ProductModel

@Serializable
data class ProductResponse(
    @SerialName("id")
    val id: String,

    @SerialName("created_at")
    val createdAt: LocalDate,

    @SerialName("name")
    val name: String,

    @SerialName("price")
    val price: Float,

    @SerialName("description")
    val description: String,

    @SerialName("validation")
    val validation: LocalDate,

    @SerialName("user_id")
    val userId: String,

    @SerialName("image_id")
    val imageId: String,

    @SerialName("category_id")
    val categoryId: String,
)

fun ProductResponse.toModel() = ProductModel(
    id,
    createdAt,
    name,
    price,
    description,
    validation,
    userId,
    imageId,
    categoryId
)
