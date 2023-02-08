package com.example.anime_list.presentation.screen.bottom_navigation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.example.anime_list.data.remote.dto.Genre

@Composable
fun OrderedListGenres(//Se crea una funcion la cual sera una lista que mostrara datos relacionados con los heroes
    modifier: Modifier,
    genres: List<Genre>,//La funcion recibira un lista con el contenido
    letterColor: Color//La funcion recibira el color de la letra
) {
    val cleanGenres = genres.map {//La lista que llega de diferentes tipos se convierte a la lista que se quiere
        it.name//Crea un map, por cada elemento "it", en el parametro "name"
    }

    Column(
        modifier = modifier
    ) {//Se declara una columna
        cleanGenres.forEachIndexed { index, item ->//De la variable recivida por cada elemento encontrado, se renombrara el indice de la lista "index" y el contenido "item"
            Text(//Se asignara un texto por cada contenido encontrado en la lista
                text = "${index + 1}. $item",//Se asignara el contenido mostrado, el cual sera el item + 1 (empieza de 0) mas el contenido "item"
                color = letterColor,//Se asigna el color de las letras
                fontSize = MaterialTheme.typography.body1.fontSize//Se asignara el tamaño de las letras
            )
        }
    }
}