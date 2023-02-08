package com.example.anime_list.presentation.screen.bottom_navigation.season

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.ErrorMessage
import com.example.anime_list.presentation.components.NetworkImage
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.theme.*
import com.example.anime_list.util.Resource

@Composable
fun SeasonUpcomingScreen(
    navigationViewModel: NavigationViewModel,
    onAnimeClick: (Int) -> Unit,//La funcion recibe una lambda que capturara el juego clicleado y este devolvera un valor de tipo "Int", referente al identificador UNICO
    listAnimeUpcomingSeason: Resource<List<AnimeShort>>//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
) {
    val background = MaterialTheme.colors.BackgroundScreen
    val backgroundCard = MaterialTheme.colors.BackgroundCard
    val letterColor = MaterialTheme.colors.TextTheme
    /** ErrorMessage */
    val imageError = R.drawable.error_image
    val messageError = R.string.Error_Message
    /** ContentHome */
    val warningIcon = Icons.Rounded.Warning

    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp / //Se crea una variable la cual almacenara la ALTURA de la pantalla y se divide
            LocalDensity.current.density

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(5.dp),
    ){
        //Se debe verificar si la lista de juegos esta vacia o no, para eso
        listAnimeUpcomingSeason.data?.let { listAnimeLast ->//Se accede a los datos "data" de variable "availableGames"
            navigationViewModel.isDetailsAnime
            if(listAnimeLast.isEmpty()){//Se pregunta, si la variable "games", esta vacia
                ErrorMessage(//Mostrara el componente
                    modifier = Modifier//Se envia un modificador
                        .align(Alignment.Center),//Se asigna la posicion
                    imageError = imageError,//Se manda la imagen de error
                    messageError = messageError,//Se manda el mensaje a mostrar
                    letterColor = letterColor//Se manda el color de la letra
                )
            }else{//Si no, quiere decir que si tiene contenido
                ContentUpcomingSeason(
                    modifier = Modifier//Se envia un modificador
                        .align(Alignment.TopCenter),//Se asigna la posicion
                    letterColor = letterColor,//Se manda el color de la letra
                    warningIcon = warningIcon,
                    animeList = listAnimeLast,
                    onAnimeClick = onAnimeClick,
                    backgroundCard = backgroundCard,
                    screenHeight = screenHeight
                )
            }
        }
    }

}

@Composable
fun ContentUpcomingSeason(
    modifier: Modifier,
    letterColor: Color,
    warningIcon: ImageVector,
    animeList: List<AnimeShort>,
    onAnimeClick: (Int) -> Unit,
    backgroundCard: Color,
    screenHeight: Dp
) {
    Column(
        modifier = modifier.fillMaxWidth(),//Se asigna un ancho completo
        verticalArrangement = Arrangement.Center//Verticalmente se centra
    ) {
        Spacer(
            modifier = Modifier.size(5.dp)
        )
        ItemsAnimeUpcoming(
            animeList = animeList,
            onAnimeClick = onAnimeClick,
            warningIcon = warningIcon,
            letterColor = letterColor,
            screenHeight = screenHeight,
            backgroundCard = backgroundCard
        )
    }
}

@Composable
fun ItemsAnimeUpcoming(
    animeList: List<AnimeShort>,
    onAnimeClick: (Int) -> Unit,
    warningIcon: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    letterColor: Color,//Recibe el color de la letra
    screenHeight: Dp,
    backgroundCard: Color//Recibe el color del fondo de la carta
) {
    LazyVerticalGrid(//Se llama al metodo "LazyVerticalGrid"
        columns = GridCells.Fixed(count = 2)//Al parametro "columns" se le asigna el numero de celdas que iran dentro
    ){
        items(items = animeList){anime ->
            AnimeCardUpcoming(
                modifier = Modifier//Se le manda el modificador
                    .padding(all = 8.dp)
                    .requiredHeight(height = screenHeight * 0.45f),//Porcentaje a ocupar por parte de UNA CARTA POR PANTALLA
                anime = anime,
                onClick = {
                    onAnimeClick(anime.id)
                },
                warningIcon = warningIcon,
                letterColor = letterColor,
                backgroundCard = backgroundCard
            )
        }
    }
}

@Composable
fun AnimeCardUpcoming(
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
                modifier = Modifier.fillMaxHeight(fraction = 0.85f),
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
            InformationAnimeUpcoming(
                anime = anime,//La funcion recibe el juego a mostrar
                letterColor = letterColor,
                modifier = Modifier.fillMaxHeight(fraction = 1f),
            )
        }
    }
}

@Composable
fun InformationAnimeUpcoming(
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
    }
}