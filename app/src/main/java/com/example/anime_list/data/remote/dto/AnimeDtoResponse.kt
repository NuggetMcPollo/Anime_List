package com.example.anime_list.data.remote.dto

data class AnimeDtoResponse(
    val `data`: List<AnimeShortDto>,
    val links: Links,
    val meta: Meta,
    val pagination: Pagination
)