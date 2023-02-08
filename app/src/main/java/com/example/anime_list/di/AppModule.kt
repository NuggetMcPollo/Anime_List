package com.example.anime_list.di

import android.content.Context
import com.example.anime_list.application.MyApp
import com.example.anime_list.data.remote.api.Api
import com.example.anime_list.data.remote.api.ApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMyApp(
        @ApplicationContext contextApp: Context//Se accede al contexto de la aplicacion y para acceder a este, se utiliza la anotacion "@ApplicationContext"
    ): MyApp {
        return contextApp as MyApp//La funcion retornara un contexto "contextApp" pero obtenido de hilt "as MyApp"
    }

    @Singleton
    @Provides
    fun providesApi(//Se crea una funcion que provea la Api
        apiBuilder: ApiBuilder//La funcion recibira un valor de tipo "ApiBuilder". OJO ESTO NO SE LLAMA POR MEDIO DE LAS ETIQUETAS LO CONSIGUE "@"
    ): Api {//Devolvera un valor de tipo "Api", la cual es la interfaz donde se declararon los metodos que consiguen la informacion de internet
        return apiBuilder.builder(Api::class.java)//Se declara bien lo que devuelve, lo cual es: La variable recibida y se "construye" dentro de la interfaz "(Api::class.java)"
    }

}