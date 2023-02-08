package com.example.anime_list.data.repository

import com.example.anime_list.data.mapper.toDomain
import com.example.anime_list.data.remote.api.Api
import com.example.anime_list.domain.model.AnimeFull
import com.example.anime_list.domain.model.AnimeGenres
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.domain.repository.AnimeRepository
import com.example.anime_list.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(//Se crea una clase para implementar los metodos del repositorio, declarados en la capa de domain
    private val api: Api//Se le inyecta la API
): BaseRepository(), AnimeRepository {

    //La clase hereda de "BaseRepository" y de "AnimeRepository"
    override suspend fun getAllAnime(): Resource<List<AnimeShort>> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getApiAnime()
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }

    override suspend fun getAnimeById(animeId: Int): Resource<AnimeFull?> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getAnimeById(animeId = animeId)
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
               data = response.data?.data?.toDomain()
            )
        }
    }

    override suspend fun getTrendAnime(): Resource<List<AnimeShort>> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getTopAnime()
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }

    override suspend fun getLastSeasonAnime(): Resource<List<AnimeShort>> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getLastSeasonAnime()
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }

    override suspend fun getSeasonNowAnime(): Resource<List<AnimeShort>> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getSeasonNowAnime()
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }

    override suspend fun getSeasonUpcomingAnime(): Resource<List<AnimeShort>> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getSeasonUpcomingAnime()
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }

    override suspend fun getAnimeGenres(): Resource<List<AnimeGenres>> {
        val response = invokeApi {//Se crea una variable llamada "response", esta tendra el resultado de llamar a la funcion "getAllGames()" ubicada en la API.
            /** OJO: COMO SE ENVUELVE DENTRO DEL METODO "invokeApi", DEVOLVERA RESPUESTAS CON LOS RESULTADOS ESTABLECIDOS DENTRO DE ELLA**/
            api.getAnimeGenres()
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }

    override suspend fun getSearchAnime(searchQuery: String): Resource<List<AnimeShort>> {
        val response = invokeApi {
            api.getSearchAnime(searchQuery = searchQuery)
        }
        return when(response){//Se establece un when y se pregunta, cuando...
            is Resource.Error -> Resource.Error(error = response.error)//La respuesta sea "Resource.Error", se devolvera "Resource.Error(error = response.error)" mandando el error de la respuesta
            is Resource.Loading -> Resource.Loading()//La respuesta sea "Resource.Loading", se devolvera "Resource.Loading()"
            is Resource.Success -> Resource.Success(//La respuesta sea "Resource.Success", se devolvera "Resource.Success(
                data = response.data?.data?.map {
                    it.toDomain()
                } ?: emptyList()//En caso de ser nulo, se manda una lista vacia
            )
        }
    }


}