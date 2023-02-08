package com.example.anime_list.data.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/** La principal razon de la creacion de la clase, es que contendra una funcion que construira una implementacion PERSONALIZADA con criterios de tipo API y por su puesto
 * devolvera una instrancia de la API**/
class ApiBuilder @Inject constructor() {

    companion object {
        const val API_BASE_URL: String = "https://api.jikan.moe/v4/"//Se crea una constante que tenga la URL base de donde se extraeran los datos
        const val CONNECTION_TIMEOUT = 1L
        const val READ_TIMEOUT = 1L
        const val WRITE_TIMEOUT = 15L
    }

    //Se crea una funcion que tendra la implementacion de la Api
    fun <Api> builder(api: Class<Api>): Api {
        return Retrofit.Builder()//La funcion devolvera
            .baseUrl(API_BASE_URL)//Se coloca URL base
            .client(getClient())//Se indica un cliente el cual sera la funcion que se declaro
            .addConverterFactory(GsonConverterFactory.create())//Se agrega el convertidor a usar, el cual serializara y deserializara los objetos
            .build()//Con el metodo build se crea la instancia usando la configuracion atras declarada
            .create(api)
    }

    /** Se crea una funcion privada la cual servira de cliente HTTP ¿Porque crear una instancia nueva? De este modo se tiene mas control al momento de las llamadas:
        - Reduce latencia
        - Ahorra memoria
    */
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()//La funcion retornara un valor de tipo "OkHttpClient.Builder()"
            .addInterceptor {//Se colocara un "Interceptor", para crear un mecanismo de vigilancia para reintentar y reescribir llamadas HTTP
                chain ->//Se renombra la lambda
                    chain.proceed(//Se coloca un proceed, de modo que se traduzca como "Se esta produciendo una cadena para satisfacer la solicitud"
                        chain.request().newBuilder().also {//"Se esta solicitando una cadena para el nuevo constructor y tambien..."
                            it.addHeader("X-Requested-With", "XMLHttpRequest")
                                .addHeader("content-type", "application/json")
                        }.build()
                    )
            }.also { client ->
                client.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES)//Para el "cliente", se configura un tiempo de espera de conexion expirado, en minutos
                    .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)//Un tiempo de lectura, en minutos
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//Un tiempo de escritura, en segundos
            }.build()
    }
}