package com.example.anime_list.domain.repository

import com.example.anime_list.domain.model.AnimeFull
import com.example.anime_list.domain.model.AnimeGenres
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.util.Resource

//Se crea una interface, para definir la abstraccion de los metodos de la API
interface AnimeRepository {

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getAllAnime(): Resource<List<AnimeShort>>

    //Se crea una funcion suspendida, la cual recibira un valor de tipo Int correspondiente al ID del anime y esta devolvera un valor de tipo "Resource" y traera consigo un objeto de tipo "DataFull"
    suspend fun getAnimeById(id: Int): Resource<AnimeFull?>

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getTrendAnime(): Resource<List<AnimeShort>>

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getSeasonNowAnime(): Resource<List<AnimeShort>>

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getSeasonUpcomingAnime(): Resource<List<AnimeShort>>

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getLastSeasonAnime(): Resource<List<AnimeShort>>

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getAnimeGenres(): Resource<List<AnimeGenres>>

    //Se crea una funcion suspendida y esta devolvera un valor de tipo "Resource" y traera consigo un lista de "AnimeShort"
    suspend fun getSearchAnime(searchQuery: String): Resource<List<AnimeShort>>
}