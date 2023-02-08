package com.example.anime_list.presentation.screen.bottom_navigation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.components.AnimeCard
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun ContentHome(
    modifier: Modifier,//La funcion recibe un modificador
    @StringRes firstText: Int,//Recibe el mensaje de la parte de arriba
    letterColor: Color,//Recibe el color de la letra
    urls: List<String>,//La funcion recivira una lista de tipo "String", correspondientes a la imagen que mostrara en el carrusel
    warningIcon: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    @StringRes secondText: Int,//Recibe el mensaje de la parte de arriba
    animeList: List<AnimeShort>,
    onAnimeClick: (Int) -> Unit,
    screenHeight: Dp,
    backgroundCard: Color//Recibe el color del fondo de la carta
) {
    Column(
        modifier = modifier.fillMaxWidth(),//Se asigna un ancho completo
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
        CarouselAnime(
            modifier = Modifier//Al parametro "modifier", se le manda el modificador y se asignan modificaciones extras
                .requiredHeight(height = 260.dp)//Se le asigna un largo
                .fillMaxWidth()//Se asigna un ancho completo
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 8.dp, horizontal = 12.dp),//Se asigna un padding
            urls = urls,
            warningIcon = warningIcon,
        )
        Spacer(
            modifier = Modifier.size(5.dp)
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = stringResource(secondText),
            color = letterColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
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

@Composable
fun ItemsAnime(
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