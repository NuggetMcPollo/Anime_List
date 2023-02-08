package com.example.anime_list.presentation.screen.bottom_navigation.season

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.ErrorMessage
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.home.ItemsAnime
import com.example.anime_list.presentation.theme.BackgroundCard
import com.example.anime_list.presentation.theme.BackgroundScreen
import com.example.anime_list.presentation.theme.TextTheme
import com.example.anime_list.util.Resource

@Composable
fun SeasonNowScreen(
    navigationViewModel: NavigationViewModel,
    onAnimeClick: (Int) -> Unit,//La funcion recibe una lambda que capturara el juego clicleado y este devolvera un valor de tipo "Int", referente al identificador UNICO
    listAnimeNowSeason: Resource<List<AnimeShort>>,//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
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
        listAnimeNowSeason.data?.let { listAnimeLast ->//Se accede a los datos "data" de variable "availableGames"
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
                ContentNowSeason(
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
fun ContentNowSeason(
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
        ItemsAnime(
            animeList = animeList,
            onAnimeClick = onAnimeClick,
            warningIcon = warningIcon,
            letterColor = letterColor,
            screenHeight = screenHeight,
            backgroundCard = backgroundCard
        )
    }
}
