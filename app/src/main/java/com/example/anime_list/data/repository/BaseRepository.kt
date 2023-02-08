package com.example.anime_list.data.repository

import com.example.anime_list.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//El propósito de la clase "", es tener una funcion que ayudara a los metodos declarado en la INTERFACE API
abstract class BaseRepository {

    /** Se crea una funcion "suspend" ya que correra de modo asincrono. Su proposito es invocar o llamar a los metodos de la API [Donde se declaro, los endpoints], el tipo genero, basicamente seran los datos que la API devolvera*/
    suspend fun <T> invokeApi(//La funcion tendra un tipo de retorno <T>
        apiCall: suspend () -> T//Recibira un valor de tipo "lambda suspend" y tendra un tipo de retorno de tipo T
    ): Resource<T> {//La funcion devuelve un valor de tipo "Resource<T>"
        return withContext(Dispatchers.IO) {//La funcion regresara en UNA CORRUTINA
            try {//En caso de ser exitoso
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {//En caso de fallar
                Resource.Error(error = throwable)
            }
        }
    }
}