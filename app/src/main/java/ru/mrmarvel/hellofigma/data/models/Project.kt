package ru.mrmarvel.hellofigma.data.models

import com.google.gson.annotations.SerializedName

data class Project(
    val id: Int,
    val title: String,
    val address: String,
    val coordinates: String,
    @SerializedName("area_coordinates")
    val areaCoordinates: String
)