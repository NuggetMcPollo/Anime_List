package com.example.anime_list.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp//Con la anotacion "@HiltAndroidApp" indica que se usara Hilt en la aplicacion
/**OJO: RECUERDA AGREGAR EN EL MANIFEST Y SE INDICA EN DONDE SE QUIEREN INYECTAR LAS DEPENDENCIAS */
class MyApp: Application(){
}