package com.example.anime_list.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.theme.Orange
import com.example.anime_list.presentation.theme.Star

@Composable
fun AnimeCard(
    anime: AnimeShort,//La funcion recibe el juego a mostrar
    modifier: Modifier = Modifier,//La funcion recibe un modificador
    onClick: () -> Unit,//La funcion recibe la lambda que capturara el click
    warningIcon: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    letterColor: Color,//Recibe el color de la letra
    backgroundCard: Color//Recibe el color del fondo de la carta
) {
    Card(//Se llama al metodo "card"
        shape = MaterialTheme.shapes.small,//Se declara la figura
        modifier = modifier//Esta recibira un modificador
            .padding(all = 8.dp)//Se asigna un padding
            .clickable {
                onClick()//El elemento, sera clicleable y se le asigna la lambda
            },
        elevation = 8.dp,//Tiene una elevacion
        border = BorderStroke(1.dp, Orange),
        backgroundColor = backgroundCard,//Se asigna un fondo
    ) {
        Column(
            modifier = Modifier.fillMaxSize()//Se aplica una columan de tamaño completo
        ) {
            NetworkImage(//Se llama a la funcion "NetworkImage" referente al componente que contendra al imagen del juego
                url = anime.images.jpg.image_url,//Al parametro "url", se le asinga lo que tiene la variable recivida "game" en el atributo "thumbnail"
                contentScale = ContentScale.Crop,//Al parametro "contentScale", se le asinga lo que tiene la variable recivida "game" en el atributo "thumbnail"
                crossFade = 1000,//Se le asigna UN SEGUNDO
                modifier = Modifier.fillMaxHeight(fraction = 0.70f),
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
                            imageVector = warningIcon,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            )
            InformationAnime(
                anime = anime,//La funcion recibe el juego a mostrar
                letterColor = letterColor,
                modifier = Modifier.fillMaxHeight(fraction = 1f),
            )
        }
    }
}

@Composable
fun InformationAnime(
    anime: AnimeShort,//La funcion recibe el juego a mostrar
    letterColor: Color,//Recibe el color de la letra
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier.size(3.dp)
        )
        Text(
            text = anime.title,//Al parametro "text", se le asinga lo que tiene la variable recivida "game" en el atributo "title"
            modifier = Modifier
                .padding(all = 5.dp)//Se asigna un padding
                .height(30.dp)
                .width(160.dp)
                .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
            style = MaterialTheme.typography.body1,//Se asigna un estilo
            overflow = TextOverflow.Ellipsis,//Se asigna un overflow
            color = letterColor,//Se asigna el color
        )
        Spacer(
            modifier = Modifier.size(2.dp)
        )
        Score(
            modifier = Modifier
                .padding(start = 10.dp),//Se asigna un padding
            anime = anime,
            letterColor = letterColor//Recibe el color de la letra
        )
        Spacer(
            modifier = Modifier.size(6.dp)
        )
        AiringItemAnime(
            modifier = Modifier
                .padding(start = 10.dp),//Se asigna un padding
            anime = anime
        )
    }
}

@Composable
fun Score(
    letterColor: Color,//Recibe el color de la letra
    anime: AnimeShort,//La funcion recibe el juego a mostrar
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Text(
            text = anime.score.toString(),
            color = letterColor,//Se asigna el color
            fontSize = 17.sp,
        )
        Spacer(
            modifier = Modifier.size(5.dp)
        )
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = Star
        )
    }
}

@Composable
fun AiringItemAnime(
    anime: AnimeShort,//La funcion recibe el juego a mostrar
    modifier: Modifier = Modifier
){
    if (anime.airing){
        Text(
            modifier = modifier,
            text = "Emisión",
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )
    }else{
        Text(
            modifier = modifier,
            text = "Finalizado",
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}