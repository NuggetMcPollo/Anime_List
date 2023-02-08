package com.example.anime_list.util

import androidx.navigation.NavHostController
import com.example.anime_list.domain.model.AnimeShort

//Se crea una extension de la lista "DataShort"
fun List<AnimeShort>.getUrls(): List<String>{//La funcion devolvera un listado de tipo "String" [Estas corresponderan a las URLs]
    return takeRandomElements(numberOfElements = 3).map {//La funcion regresara la lista dada de la funcion "takeRandomElements", REGRESA 3 ELEMENTOS y esos elementos los tomara del mapero de
        it.images.jpg.image_url//El elemento "it" en el atributo "thumbnail"
    }
}

//Se crea una funcion la dara un numero ramdom de elemento
fun <T> List<T>.takeRandomElements(numberOfElements: Int): List<T>{//La funcion devolvera un listado de "Algo", esta recibira un valor de tipo entero
    //Supongamos que tenemos 10 elementos en una lista y queremos tomar 3 elementos ramdoms aleatorios de esa lista esta regresara una lista con los 3 numeros aleatorios
    return if(numberOfElements > size) this//Para eso se pregunta si la "variable" es mas grande que el tamaño de la lista, devuelve la lista actual, de lo contrario
    else asSequence().shuffled().take(numberOfElements).toList()//"Toma la secuencia de la lista" "barajeala" y "toma" el numero de elementos pedidos y mandalos en una lista
}

inline fun NavHostController.navigate(
    route: String,
    onNavigate: () -> Unit
){
    onNavigate()
    navigate(route = route)
}