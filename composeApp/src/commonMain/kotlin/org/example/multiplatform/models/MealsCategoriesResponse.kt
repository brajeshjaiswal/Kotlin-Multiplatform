package org.example.multiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealsCategoriesResponse(
    @SerialName("categories")
    val categories: List<MealResponse>? = null
)

@Serializable
data class MealResponse(
    @SerialName("idCategory")
    val id: String? = null,
    @SerialName("strCategory")
    val name: String? = null,
    @SerialName("strCategoryThumb")
    val imageUrl: String? = null,
    @SerialName("strCategoryDescription")
    val description: String? = null,
)
