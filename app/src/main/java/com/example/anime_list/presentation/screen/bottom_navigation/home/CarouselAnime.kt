package com.example.anime_list.presentation.screen.bottom_navigation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.anime_list.presentation.components.NetworkImage
import com.example.anime_list.presentation.theme.IndicatorPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun CarouselAnime(
    urls: List<String>,//La funcion recivira una lista de tipo "String", correspondientes a la imagen que mostrara en el carrusel
    modifier: Modifier = Modifier,//Recivira un modificador
    contentScale: ContentScale = ContentScale.Crop,//La funcion recibira una variable para escalar las imagenes recividas
    pagerIndicatorColor: Color = IndicatorPager,//Se asigna un color para el indicador
    shape: Shape = RectangleShape,//Se asigna un forma
    crossFade: Int? = null,
    warningIcon: ImageVector,//La funcion recibira un icon en caso de fallar la carga
) {
    Box(//Se asigna una caja
        modifier = modifier,//Se asigna el modificador
        contentAlignment = Alignment.TopCenter//Se asigna la posicion
    ) {
        val pagerState = rememberPagerState(initialPage = urls.size)//Se crea una variable que recordara el estado del "PagerState"
        //val pagerState = rememberPagerState()//Se crea una variable que recordara el estado del "PagerState"

        HorizontalPager(//Se llama a la funcion "HorizontalPager"
            state = pagerState,//Al parametro "state", se le pasa la variable pagerState la cual recuerda el estado
            count = urls.size,//El tamaño de la pagina sera el sacado del tamaño de la lista de URLs "urls.size"
            modifier = Modifier//Se asigna el modificador
                .fillMaxWidth()//Se asigna un ancho completo
                .fillMaxHeight()//Se asigna un largo completo
                .clip(shape = shape)
        ) { index ->
            NetworkImage(
                url = urls[index],
                crossFade = crossFade,
                contentScale = contentScale,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
                            contentDescription = "",
                            tint = Color.Red
                        )
                    }
                }
            )
        }
        HorizontalPagerIndicator(
            activeColor = pagerIndicatorColor,
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(color = Color.Transparent)
                .padding(bottom = 5.dp)
        )
    }
}