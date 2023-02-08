package com.example.anime_list.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeFullDto(
    @SerializedName("aired") val release: Aired,
    val airing: Boolean,
    val approved: Boolean,
    val background: String,
    val broadcast: Broadcast,
    val demographics: List<Any>,
    val duration: String,
    val episodes: Int,
    val explicit_genres: List<Any>,
    val external: List<Any>,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val licensors: List<Licensor>,
    @SerializedName("mal_id") val id: Int,
    val members: Int,
    val popularity: Int,
    val producers: List<Producer>,
    val rank: Int,
    val rating: String,
    val relations: List<Relation>,
    val score: Double,
    val scored_by: Int,
    val season: String,
    val source: String,
    val status: String,
    val streaming: List<Streaming>,
    val studios: List<Studio>,
    val synopsis: String,
    val theme: Theme,
    val themes: List<ThemeX>,
    val title: String,
    val title_english: String? = null,
    val title_japanese: String,
    val title_synonyms: List<Any>,
    val titles: List<Title>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
)