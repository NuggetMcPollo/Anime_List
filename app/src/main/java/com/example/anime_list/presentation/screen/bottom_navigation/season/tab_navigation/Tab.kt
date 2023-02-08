package com.example.anime_list.presentation.screen.bottom_navigation.season.tab_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.home.HomeViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.season.SeasonLastScreen
import com.example.anime_list.presentation.screen.bottom_navigation.season.SeasonNowScreen
import com.example.anime_list.presentation.screen.bottom_navigation.season.SeasonUpcomingScreen
import com.example.anime_list.presentation.theme.Orange
import com.example.anime_list.util.Resource
import com.example.anime_list.util.navigate
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun Tab_Navigator(
    navController: NavHostController,//Recivira un navcontroller
    navigationViewModel: NavigationViewModel,
    mainViewModel: HomeViewModel,
    listAnimeLastSeason: Resource<List<AnimeShort>>,//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
    listAnimeNowSeason: Resource<List<AnimeShort>>,//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
    listAnimeUpcomingSeason: Resource<List<AnimeShort>>//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
) {
    navigationViewModel.setTitlePage(title = stringResource(R.string.Season))

    val list = listOf("Anterior", "Ahora", "Proximamente")

    val pagerState = rememberPagerState(1)

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(
            pagerState = pagerState,
            list = list
        )
        TabsContent(
            list = list,
            pagerState = pagerState,
            navController = navController,//Recivira un navcontroller
            navigationViewModel = navigationViewModel,
            mainViewModel = mainViewModel ,
            listAnimeLastSeason = listAnimeLastSeason,
            listAnimeNowSeason = listAnimeNowSeason,
            listAnimeUpcomingSeason = listAnimeUpcomingSeason,
        )
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(
    pagerState: PagerState,
    list: List<String>
) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Orange,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 2.dp,
                color = Orange
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _->
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    list: List<String>,
    navController: NavHostController,//Recivira un navcontroller
    pagerState: PagerState,
    navigationViewModel: NavigationViewModel,
    mainViewModel: HomeViewModel,
    listAnimeLastSeason: Resource<List<AnimeShort>>,//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
    listAnimeNowSeason: Resource<List<AnimeShort>>,//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
    listAnimeUpcomingSeason: Resource<List<AnimeShort>>//Se crea una variable "availableGames - juegos disponibles" la cual sera de tipo "Resource" y traera consigo una lista de game
) {
    HorizontalPager(
        state = pagerState,
        count = list.size
    ) { page ->
        when(page) {
            0 -> SeasonLastScreen(
                    navigationViewModel = navigationViewModel,
                    onAnimeClick = { animeId ->//A la lambda que tomara el click de juego presionado y esta como sera el id, se tomara este y se mandara a la ruta a la cual se redirigira
                        val path = "animeDetail/$animeId"//Se crea una variable la cual, tendra la RUTA DETINO A LA CUAL NEVEGARA UNA VEZ PRESIONANDO EL CLICK DEL JUEGO
                        navController.navigate(//Se le pasara el navcontroller, mas la extencion personalizada de navigate, a la cual...
                            route = path,//Se le pasara la ruta, del DESTINO AL CUAL NAVEGARA
                            onNavigate = {
                                mainViewModel.setRoute(route = path)
                            }
                        )
                    },
                    listAnimeLastSeason = listAnimeLastSeason
                )
            1 -> SeasonNowScreen(
                    navigationViewModel = navigationViewModel,
                    onAnimeClick = { animeId ->//A la lambda que tomara el click de juego presionado y esta como sera el id, se tomara este y se mandara a la ruta a la cual se redirigira
                        val path = "animeDetail/$animeId"//Se crea una variable la cual, tendra la RUTA DETINO A LA CUAL NEVEGARA UNA VEZ PRESIONANDO EL CLICK DEL JUEGO
                        navController.navigate(//Se le pasara el navcontroller, mas la extencion personalizada de navigate, a la cual...
                            route = path,//Se le pasara la ruta, del DESTINO AL CUAL NAVEGARA
                            onNavigate = {
                                mainViewModel.setRoute(route = path)
                            }
                        )
                    },
                    listAnimeNowSeason = listAnimeNowSeason
                )
            2 -> SeasonUpcomingScreen(
                    navigationViewModel = navigationViewModel,
                    onAnimeClick = { animeId ->//A la lambda que tomara el click de juego presionado y esta como sera el id, se tomara este y se mandara a la ruta a la cual se redirigira
                        val path = "animeDetail/$animeId"//Se crea una variable la cual, tendra la RUTA DETINO A LA CUAL NEVEGARA UNA VEZ PRESIONANDO EL CLICK DEL JUEGO
                        navController.navigate(//Se le pasara el navcontroller, mas la extencion personalizada de navigate, a la cual...
                            route = path,//Se le pasara la ruta, del DESTINO AL CUAL NAVEGARA
                            onNavigate = {
                                mainViewModel.setRoute(route = path)
                            }
                        )
                    },
                    listAnimeUpcomingSeason = listAnimeUpcomingSeason
                )
        }
    }
}

