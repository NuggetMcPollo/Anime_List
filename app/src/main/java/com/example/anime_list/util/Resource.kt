package com.example.anime_list.util

//El principal motivo de este sealed es mostrar los estados, al momento de cargar los datos
sealed class Resource<T>(//Sera de tipo generico
    val data: T? = null,//Se crea una variable de tipo "Cualquier tipo" con valor definido null
    val error: Throwable? = null//Se crea una variable para capturar errores de tipo "Throwable" con valor definido null
) {
    class Loading<T>(data: T? = null): Resource<T>(data = data)//Se crea la clase  "Loading", la cual posiblemente tenga un dato o no. Esto seria en un futuro en caso de tener un funcion de cache. Ya que si no se tienen datos
    // por eso mismo si no se tiene datos, se muestran los datos de la cache. Esta clase hereda de "Resource" y sera de tipo <T> y regresa los datos
    class Success<T>(data: T): Resource<T>(data = data)//Se crea un data class llamada "Success" la cual tiene un dato de tipo T: La cual proporcionara los datos y este hereda de "RequestState" el cual sera de tipo <T>
    //y tendra el valor que recivido
    class Error<T>(data: T? = null, error: Throwable? = null): Resource<T>(data = data, error = error)//Se crea un data class llamada "Error" la cual tiene un dato de tipo Throwable, y eso es porque obtendra un error y, el cual hereda de "RequestState"
    //el cual sera de tipo <T> y tendra el error recivido
}