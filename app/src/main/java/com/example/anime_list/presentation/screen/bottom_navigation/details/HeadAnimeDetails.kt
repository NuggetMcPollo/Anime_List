package com.example.anime_list.presentation.screen.bottom_navigation.details

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anime_list.domain.model.AnimeFull
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.NetworkImage
import com.example.anime_list.presentation.theme.Star

@Composable
fun HeadAnimeDetails(
    animeDetails: AnimeFull,//La funcion recibira la lista que contiene la informacion del anime
    warningIcon: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    @StringRes japanese: Int,//Recibe el mensaje del error
    @StringRes english: Int,//Recibe el mensaje del error
    @StringRes score: Int,//Recibe el mensaje del error
    @StringRes airing: Int,//Recibe el mensaje del error
    @StringRes release: Int,//Recibe el mensaje del error
    letterColor: Color//Recibe el color de la letra
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()//Se asigna un ancho completo
            .fillMaxHeight(fraction = 0.7f)
            .padding(8.dp),//Se asigna un padding
        verticalAlignment = Alignment.CenterVertically,//Se centra el contenido verticalmente
    ){
        NetworkImage(//Se llama a la funcion "NetworkImage" referente al componente que contendra al imagen del juego
            url = animeDetails.images.jpg.image_url,//Al parametro "url", se le asinga lo que tiene la variable recivida "game" en el atributo "thumbnail"
            contentScale = ContentScale.Crop,//Al parametro "contentScale", se le asinga lo que tiene la variable recivida "game" en el atributo "thumbnail"
            crossFade = 1000,//Se le asigna UN SEGUNDO
            modifier = Modifier
                .fillMaxWidth()//Se asigna un ancho completo
                .weight(1f),//Se asigna un peso
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
        InformationAnimeFullRight(
            modifier = Modifier
                .fillMaxWidth()//Se asigna un ancho completo
                .weight(1f),//Se asigna un peso
            animeDetails = animeDetails,
            japanese = japanese,
            english = english,
            score = score,
            airing = airing,
            release = release,
            letterColor = letterColor
        )
    }
}

@Composable
fun InformationAnimeFullRight(
    modifier: Modifier = Modifier,
    animeDetails: AnimeFull,//La funcion recibira la lista que contiene la informacion del anime
    @StringRes japanese: Int,//Recibe el mensaje del error
    @StringRes english: Int,//Recibe el mensaje del error
    @StringRes score: Int,//Recibe el mensaje del error
    @StringRes airing: Int,//Recibe el mensaje del error
    @StringRes release: Int,//Recibe el mensaje del error
    letterColor: Color//Recibe el color de la letra
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(japanese),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    color = letterColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = animeDetails.title_japanese,//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(40.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.body1,//Se asigna un estilo
                    color = letterColor,//Se asigna el color
                )
            }
            animeDetails.title_english?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),//Se asigna un peso
                ) {
                    Text(
                        text = stringResource(english),//Se asigna el mensaje a mostrar
                        modifier = Modifier
                            .padding(all = 5.dp)//Se asigna un padding
                            .height(30.dp)
                            .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                        color = letterColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = it,//Se asigna el mensaje a mostrar
                        modifier = Modifier
                            .padding(all = 5.dp)//Se asigna un padding
                            .height(40.dp)
                            .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        style = MaterialTheme.typography.body1,//Se asigna un estilo
                        color = letterColor,//Se asigna el color
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(score),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    color = letterColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(5.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)//Se centra el contenido
                        .height(40.dp),
                ){
                    Text(
                        text = animeDetails.score.toString(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.body1,//Se asigna un estilo
                        color = letterColor,//Se asigna el color
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = Star,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(airing),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    color = letterColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(5.dp))
                AiringFullAnime(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)//Se centra el contenido
                        .height(40.dp),
                    animeDetails = animeDetails
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(release),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    color = letterColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "${animeDetails.release.prop.from.year}/${animeDetails.release.prop.from.month}/${animeDetails.release.prop.from.day}",
                        modifier = Modifier
                            .padding(all = 5.dp)//Se asigna un padding
                            .height(30.dp),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,//Se asigna un estilo
                        color = letterColor,//Se asigna el color
                    )
                    Text(
                        text = " - ",
                        modifier = Modifier
                            .padding(all = 5.dp)//Se asigna un padding
                            .height(30.dp),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,//Se asigna un estilo
                        color = letterColor,//Se asigna el color
                    )
                    Text(
                        text = "${animeDetails.release.prop.to.year}/${animeDetails.release.prop.to.month}/${animeDetails.release.prop.to.day}",
                        modifier = Modifier
                            .padding(all = 5.dp)//Se asigna un padding
                            .height(30.dp),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,//Se asigna un estilo
                        color = letterColor,//Se asigna el color
                    )
                }
            }
        }
    }
}

@Composable
fun AiringFullAnime(
    animeDetails: AnimeFull,//La funcion recibira la lista que contiene la informacion del anime
    modifier: Modifier = Modifier
){
    if (animeDetails.airing){
        Text(
            modifier = modifier,
            text = "Emisión",
            color = Color.Green,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }else{
        Text(
            modifier = modifier,
            text = "Finalizado",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}