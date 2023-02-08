package com.example.anime_list.data.remote.api

import com.example.anime_list.data.remote.dto.AnimeDtoResponse
import com.example.anime_list.data.remote.dto.AnimeDtoFilterResponse
import com.example.anime_list.data.remote.dto.AnimeFullResponse
import com.example.anime_list.data.remote.dto.AnimeGenresDtoResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {

    @GET("anime")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getApiAnime(): AnimeDtoResponse

    @GET("anime/{anime_id}/full")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la UN ANIME de la api **/
    suspend fun getAnimeById(
        @Path("anime_id") animeId: Int
    ): AnimeFullResponse

    @GET("anime?q={searchQuery}&sfw")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getSearchAnime(
        @Path("searchQuery") searchQuery: String
    ): AnimeDtoResponse

    @GET("top/anime")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getTopAnime(): AnimeDtoFilterResponse

    @GET("seasons/2022/fall")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getLastSeasonAnime(): AnimeDtoFilterResponse

    @GET("seasons/now")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getSeasonNowAnime(): AnimeDtoFilterResponse

    @GET("seasons/upcoming")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getSeasonUpcomingAnime(): AnimeDtoFilterResponse

    @GET("genres/anime")//Se le agrega la anotacion "@GET" y posteriormente se indica el endpoint del servidor de donde obtendra los datos
    /** Esta funcion es de tipo "suspend" la cual se encargara de traer la informacion de la api **/
    suspend fun getAnimeGenres(): AnimeGenresDtoResponse




/*
    //https://api.jikan.moe/v4/anime?q=nagatoro&sfw
    @GET("anime?q={query}&sfw")
    suspend fun sortAnimeTitle(
        @Query("query") query: String
    ): AnimeDtoResponse

*/


}