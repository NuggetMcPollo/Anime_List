package com.example.anime_list.domain.model

import com.example.anime_list.data.remote.dto.*

data class AnimeFull(
    val id: Int,
    val images: Images,
    val title: String,
    val title_english: String? = null,
    val title_japanese: String,
    val score: Double,
    val airing: Boolean,
    val release: Aired,
    val synopsis: String,
    val episodes: Int,
    val duration: String,
    val genres: List<Genre>,
    val streaming: List<Streaming>,
    val trailer: Trailer,
)
