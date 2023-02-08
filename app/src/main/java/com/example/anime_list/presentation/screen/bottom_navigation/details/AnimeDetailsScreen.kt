package com.example.anime_list.presentation.screen.bottom_navigation.details

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Login
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeFull
import com.example.anime_list.presentation.components.ErrorMessage
import com.example.anime_list.presentation.components.ExpandableText
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.theme.BackgroundScreen
import com.example.anime_list.presentation.theme.TextTheme
import com.example.anime_list.util.Resource

@Composable
fun AnimeDetailsScreen(
    navigationViewModel: NavigationViewModel,
    detailsViewModel: DetailsViewModel,
    navController: NavHostController,
    onTrailerClicked: (String) -> Unit
){

    BackHandler {//Esta funcion cuando es precionado el boton de atras, dentro del sistema en las direcciones
        navController.navigateUp()//El navegador, pero accediendo al metodo "navigateUp"
    }

    val animeDetails by detailsViewModel.animeFull.collectAsState()

    val background = MaterialTheme.colors.BackgroundScreen
    val letterColor = MaterialTheme.colors.TextTheme

    /** ErrorMessage */
    val imageError = R.drawable.error_image
    val messageError = R.string.Error_Anime
    val warningIcon = Icons.Rounded.Warning

    /** InformationAnimeFullRight */
    val japanese = R.string.Language_Japanese
    val english = R.string.Language_English
    val score = R.string.Score
    val airing = R.string.Airing
    val release = R.string.Release

    /** InformationAnimeFullBottom */
    val iconTrailer = Icons.Rounded.Login//La funcion recibira un icon en caso de fallar la carga
    val synopsis = R.string.Synopsis
    val episodes = R.string.Episodes
    val duration = R.string.Duration
    val genres = R.string.Genres
    val streaming = R.string.Streaming
    val trailer = R.string.Trailer


    if(animeDetails is Resource.Loading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }

    if(animeDetails is Resource.Error){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ErrorMessage(//Mostrara el componente
                modifier = Modifier//Se envia un modificador
                    .align(Alignment.Center),//Se asigna la posicion
                imageError = imageError,//Se manda la imagen de error
                messageError = messageError,//Se manda el mensaje a mostrar
                letterColor = letterColor//Se manda el color de la letra
            )
        }
    }

    if(animeDetails is Resource.Success){
        animeDetails.data?.let { animeDetails ->//Se accede a los datos "data" de variable "availableGames"
            navigationViewModel.setTitlePage(title = animeDetails.title)//Se canbia el titulo de colocado arriba
            Column(//Se coloca una columna
                modifier = Modifier
                    .background(background)
                    .fillMaxSize()//Se tamaño completo
                    .verticalScroll(state = rememberScrollState())//El cual sera de tipo scrolleable
            ) {
                HeadAnimeDetails(
                    animeDetails = animeDetails,
                    warningIcon = warningIcon,
                    japanese = japanese,
                    english = english,
                    score = score,
                    airing = airing,
                    release = release,
                    letterColor = letterColor
                )
                Spacer(modifier = Modifier.size(10.dp))
                BodyAnimeDetails(
                    animeDetails = animeDetails,
                    synopsis = synopsis,
                    episodes = episodes,
                    duration = duration,
                    genres = genres,
                    streaming = streaming,
                    letterColor = letterColor,
                    trailer = trailer,
                    iconTrailer = iconTrailer,
                    onTrailerClicked = onTrailerClicked
                )
            }
        }
    }
}

@Composable
fun BodyAnimeDetails(
    animeDetails: AnimeFull,//La funcion recibira la lista que contiene la informacion del anime
    iconTrailer: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    @StringRes synopsis: Int,//Recibe el mensaje del error
    @StringRes episodes: Int,//Recibe el mensaje del error
    @StringRes duration: Int,//Recibe el mensaje del error
    @StringRes genres: Int,//Recibe el mensaje del error
    @StringRes streaming: Int,//Recibe el mensaje del error
    @StringRes trailer: Int,//Recibe el mensaje del error
    letterColor: Color,//Recibe el color de la letra
    onTrailerClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = stringResource(synopsis),
            color = letterColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        ExpandableText(//Se llama al componentente "ExpandableText+"
            text = animeDetails.synopsis,//Se le manda la parte de la descripcion
            color = letterColor,//Se le manda el color del texto
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 15.sp,
            textAlign = TextAlign.Justify,
            buttonColor = letterColor
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(episodes),//Se asigna el mensaje a mostrar
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
                    text = animeDetails.episodes.toString(),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    style = MaterialTheme.typography.body1,//Se asigna un estilo
                    color = letterColor,//Se asigna el color
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(duration),//Se asigna el mensaje a mostrar
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
                    text = animeDetails.duration,//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    style = MaterialTheme.typography.body1,//Se asigna un estilo
                    color = letterColor,//Se asigna el color
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(genres),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    color = letterColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(5.dp))
                OrderedListGenres(
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(150.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    genres = animeDetails.genres,
                    letterColor = letterColor
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),//Se asigna un peso
            ) {
                Text(
                    text = stringResource(streaming),//Se asigna el mensaje a mostrar
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    color = letterColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(5.dp))
                OrderedListStreaming(
                    modifier = Modifier
                        .padding(all = 5.dp)//Se asigna un padding
                        .height(150.dp)
                        .align(alignment = Alignment.CenterHorizontally),//Se centra el contenido
                    streaming = animeDetails.streaming,
                    letterColor = letterColor
                )
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        LeadingIconButton(//Se llama al composable "LeadingIconButton"
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            iconTrailer = iconTrailer,
            textButton = stringResource(id = trailer),
            onClick = {
                onTrailerClicked(animeDetails.trailer.url)//Se pasa a la lambda que tomara el click del link a mandar a la pagina
            }
        )
        Spacer(modifier = Modifier.height(height = 20.dp))
    }
}