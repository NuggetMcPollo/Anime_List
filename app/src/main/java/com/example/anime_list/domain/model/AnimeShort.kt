package com.example.anime_list.domain.model

import com.example.anime_list.data.remote.dto.*

data class AnimeShort (
    val id: Int,
    val images: Images,
    val title: String,
    val score: Double,
    val airing: Boolean,
)