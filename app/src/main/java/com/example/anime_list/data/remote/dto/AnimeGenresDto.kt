package com.example.anime_list.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeGenresDto(
    val count: Int,
    @SerializedName("mal_id") val id: Int,
    val name: String,
    val url: String
)