package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.NetworkImage

//Se crea el la funcion correspondiente al item cuando se muestra como sugerencia
@Composable
fun SearchDetailCard(
    anime: AnimeShort,//La funcion recibe el juego
    onClick: (Int) -> Unit//La funcion recibe una lambda y devuelve un entero correspondiente al id del juego seleccionado
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,//Se asigna un background referente al fondo de la carta
        shape = MaterialTheme.shapes.medium,//Se asigna una forma
        elevation = 8.dp,//Se agrega una elevacion
        modifier = Modifier
            .requiredHeight(height = 150.dp)//Se agrega una altura
            .fillMaxWidth()//Se asigna un largo completo
            .clickable { //Se puede clickear
                onClick(anime.id)//Se asigna la lambda y esta recibe el id del juego seleccionado
            }
    ){
        Row(modifier = Modifier.fillMaxSize()){
            NetworkImage(
                url = anime.title,
                contentScale = ContentScale.Crop,
                crossFade = 1000,
                modifier = Modifier.fillMaxWidth(fraction = 0.3f),
                onLoading = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                },
                onError = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            )
            Column {
                Text(
                    text = anime.title,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = anime.title,
                    modifier = Modifier.padding(all = 5.dp)
                        .fillMaxWidth(fraction = 0.85f),
                    style = MaterialTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }

}