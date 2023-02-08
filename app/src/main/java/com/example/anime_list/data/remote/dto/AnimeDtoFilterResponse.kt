package com.example.anime_list.data.remote.dto

data class AnimeDtoFilterResponse(
    val `data`: List<AnimeShortDto>,
    val pagination: Pagination
)