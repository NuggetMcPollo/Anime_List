package com.example.anime_list.presentation.screen.bottom_navigation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.ErrorMessage
import com.example.anime_list.util.Resource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.anime_list.presentation.theme.BackgroundScreen
import com.example.anime_list.presentation.theme.TextTheme
import com.example.anime_list.util.getUrls
import com.example.anime_list.R
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.theme.BackgroundCard

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navigationViewModel: NavigationViewModel,
    onAnimeClick: (Int) -> Unit,//La funcion recibe una lambda que capturara el juego clicleado y este devolvera un valor de tipo "Int", referente al identificador UNICO
    allAnime: Resource<List<AnimeShort>>//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
){
    navigationViewModel.setTitlePage(title = stringResource(R.string.Home_Page))
    val background = MaterialTheme.colors.BackgroundScreen
    val backgroundCard = MaterialTheme.colors.BackgroundCard
    val letterColor = MaterialTheme.colors.TextTheme
    /** ErrorMessage */
    val imageError = R.drawable.error_image
    val messageError = R.string.Error_Message
    /** ContentHome */
    val firstText = R.string.Recommendation
    val warningIcon = Icons.Rounded.Warning
    val secondText = R.string.All_Anime

    val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp / //Se crea una variable la cual almacenara la ALTURA de la pantalla y se divide
            LocalDensity.current.density
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(5.dp),
    ){
        //Se debe verificar si la lista de juegos esta vacia o no, para eso
        allAnime.data?.let { anime ->//Se accede a los datos "data" de variable "availableGames"
            navigationViewModel.isDetailsAnime
            if(anime.isEmpty()){//Se pregunta, si la variable "games", esta vacia
                ErrorMessage(//Mostrara el componente
                    modifier = Modifier//Se envia un modificador
                        .align(Alignment.Center),//Se asigna la posicion
                    imageError = imageError,//Se manda la imagen de error
                    messageError = messageError,//Se manda el mensaje a mostrar
                    letterColor = letterColor//Se manda el color de la letra
                )
            }else{//Si no, quiere decir que si tiene contenido
                ContentHome(
                    modifier = Modifier//Se envia un modificador
                        .align(Alignment.TopCenter),//Se asigna la posicion
                    firstText = firstText,
                    letterColor = letterColor,//Se manda el color de la letra
                    urls = anime.getUrls(),
                    warningIcon = warningIcon,
                    secondText = secondText,
                    animeList = anime,
                    onAnimeClick = onAnimeClick,
                    backgroundCard = backgroundCard,
                    screenHeight = screenHeight
                )
            }
        }
    }
}
