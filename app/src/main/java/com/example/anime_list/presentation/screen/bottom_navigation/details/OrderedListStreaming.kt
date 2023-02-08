package com.example.anime_list.presentation.screen.bottom_navigation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anime_list.R
import com.example.anime_list.data.remote.dto.Streaming

@Composable
fun OrderedListStreaming(//Se crea una funcion la cual sera una lista que mostrara datos relacionados con los heroes
    modifier: Modifier,
    streaming: List<Streaming>,//La funcion recibira un lista con el contenido
    letterColor: Color//La funcion recibira el color de la letra
) {
    val cleanStreaming = streaming.map {//La lista que llega de diferentes tipos se convierte a la lista que se quiere
        it.name//Crea un map, por cada elemento "it", en el parametro "name"
    }

    Column (
        modifier = modifier
    ){//Se declara una columna
        cleanStreaming.forEachIndexed { index, streamingPlatform ->//De la variable recivida por cada elemento encontrado, se renombrara el indice de la lista "index" y el contenido "item"
            val imagePlatform = if (streamingPlatform.contains("Crunchyroll", ignoreCase = true)) {//Se crea una variable que cambie dependiendo la situacion, para eso se pregunta si el texto que tiene
                //la variable "text", es igual a "windows", se asigna
                R.drawable.crunchyroll
            } else if(streamingPlatform.contains("Funimation", ignoreCase = true)){
                R.drawable.funimation
            } else if(streamingPlatform.contains("Netflix", ignoreCase = true)){
                R.drawable.netflix
            } else {//Si no, quiere decir que no esta contemplado el caso y se asigna un valor por defecto
                R.drawable.ic_launcher_foreground
            }
            Spacer(
                modifier = Modifier.size(10.dp)
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = imagePlatform),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )
                Spacer(
                    modifier = Modifier.size(8.dp)
                )
                Text(
                    text = streamingPlatform,
                    color = letterColor,//Se asigna el color
                    fontSize = 18.sp,
                )
            }
        }
    }
}