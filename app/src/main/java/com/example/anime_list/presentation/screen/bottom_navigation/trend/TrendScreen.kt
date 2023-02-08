package com.example.anime_list.presentation.screen.bottom_navigation.trend

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.AnimeCard
import com.example.anime_list.presentation.components.ErrorMessage
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.anime_list.presentation.theme.BackgroundCard
import com.example.anime_list.presentation.theme.BackgroundScreen
import com.example.anime_list.presentation.theme.TextTheme
import com.example.anime_list.util.Resource

@Composable
fun TrendScreen(
    navigationViewModel: NavigationViewModel,
    onAnimeClick: (Int) -> Unit,//La funcion recibe una lambda que capturara el juego clicleado y este devolvera un valor de tipo "Int", referente al identificador UNICO
    getTopAnime: Resource<List<AnimeShort>>,//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
    allAnime: Resource<List<AnimeShort>>//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
) {
    navigationViewModel.setTitlePage(title = stringResource(R.string.Trend))
    val background = MaterialTheme.colors.BackgroundScreen
    val backgroundCard = MaterialTheme.colors.BackgroundCard
    val letterColor = MaterialTheme.colors.TextTheme

    /** ErrorMessage */
    val imageError = R.drawable.error_image
    val messageError = R.string.Error_Message

    /** ContentHome */
    val firstText = R.string.Top_Anime
    val warningIcon = Icons.Rounded.Warning
    val secondText = R.string.Top_Reviews

    val screenHeight =
        LocalContext.current.resources.displayMetrics.heightPixels.dp / //Se crea una variable la cual almacenara la ALTURA de la pantalla y se divide
                LocalDensity.current.density
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(5.dp),
    ) {
        //Se debe verificar si la lista de juegos esta vacia o no, para eso
        getTopAnime.data?.let { topAnime ->//Se accede a los datos "data" de variable "availableGames"
            allAnime.data?.let { allAnime ->//Se accede a los datos "data" de variable "availableGames"
                navigationViewModel.isDetailsAnime
                if (topAnime.isEmpty() && allAnime.isEmpty()) {//Se pregunta, si la variable "games", esta vacia
                    ErrorMessage(//Mostrara el componente
                        modifier = Modifier//Se envia un modificador
                            .align(Alignment.Center),//Se asigna la posicion
                        imageError = imageError,//Se manda la imagen de error
                        messageError = messageError,//Se manda el mensaje a mostrar
                        letterColor = letterColor//Se manda el color de la letra
                    )
                } else {//Si no, quiere decir que si tiene contenido
                    ContentTrend(
                        modifier = Modifier//Se envia un modificador
                            .align(Alignment.TopCenter),//Se asigna la posicion
                        firstText = firstText,
                        letterColor = letterColor,//Se manda el color de la letra
                        warningIcon = warningIcon,
                        secondText = secondText,
                        topAnime = topAnime,
                        onAnimeClick = onAnimeClick,
                        backgroundCard = backgroundCard,
                        screenHeight = screenHeight,
                        allAnime = allAnime
                    )
                }
            }
        }
    }
}

@Composable
fun ContentTrend(
    modifier: Modifier,//La funcion recibe un modificador
    @StringRes firstText: Int,//Recibe el mensaje de la parte de arriba
    letterColor: Color,//Recibe el color de la letra
    warningIcon: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    @StringRes secondText: Int,//Recibe el mensaje de la parte de arriba
    topAnime: List<AnimeShort>,
    allAnime: List<AnimeShort>,
    onAnimeClick: (Int) -> Unit,
    screenHeight: Dp,
    backgroundCard: Color//Recibe el color del fondo de la carta
) {
    Column(
        modifier = modifier
            .fillMaxWidth()//Se asigna un ancho completo
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center//Verticalmente se centra
    ) {
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = stringResource(firstText),
            color = letterColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier.size(5.dp)
        )
        ItemsAnimeRow(
            listAnime = topAnime,
            onAnimeClick = onAnimeClick,
            warningIcon = warningIcon,
            letterColor = letterColor,
            screenHeight = screenHeight,
            backgroundCard = backgroundCard
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = stringResource(secondText),
            color = letterColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        ItemsAnimeRow(
            listAnime = allAnime,
            onAnimeClick = onAnimeClick,
            warningIcon = warningIcon,
            letterColor = letterColor,
            screenHeight = screenHeight,
            backgroundCard = backgroundCard
        )
    }
}

@Composable
fun ItemsAnimeRow(
    listAnime: List<AnimeShort>,
    onAnimeClick: (Int) -> Unit,
    warningIcon: ImageVector,
    letterColor: Color,
    screenHeight: Dp,
    backgroundCard: Color
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = listAnime) { anime ->
            AnimeCard(
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
