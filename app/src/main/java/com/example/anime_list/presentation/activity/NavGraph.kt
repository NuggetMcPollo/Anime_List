package com.example.anime_list.presentation.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.UriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.graphs.Graph
import com.example.anime_list.presentation.screen.bottom_navigation.BottomBar
import com.example.anime_list.presentation.screen.bottom_navigation.BottomBarNavigation
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.TopBarNavigation
import com.example.anime_list.presentation.screen.bottom_navigation.details.AnimeDetailsScreen
import com.example.anime_list.presentation.screen.bottom_navigation.details.DetailsViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.genders.GendersScreen
import com.example.anime_list.presentation.screen.bottom_navigation.home.HomeScreen
import com.example.anime_list.presentation.screen.bottom_navigation.home.HomeViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.search.SearchEmpty
import com.example.anime_list.presentation.screen.bottom_navigation.search.SearchScreen
import com.example.anime_list.presentation.screen.bottom_navigation.search.SearchViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.season.SeasonViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.season.tab_navigation.Tab_Navigator
import com.example.anime_list.presentation.screen.bottom_navigation.trend.TrendScreen
import com.example.anime_list.presentation.screen.bottom_navigation.trend.TrendViewModel
import com.example.anime_list.presentation.screen.splash.SplashScreen
import com.example.anime_list.util.ALL_ANIME_KEY
import com.example.anime_list.util.SEARCH_MODE_KEY
import com.example.anime_list.util.SEARCH_SCREEN_NORMAL
import com.example.anime_list.util.navigate
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun NavGraph(
    uriHandler: UriHandler,
    navigationViewModel: NavigationViewModel,
    navController: NavHostController = rememberNavController(),
    mainViewModel: HomeViewModel,
    seasonViewModel: SeasonViewModel,
    trendViewModel: TrendViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val allAnime by mainViewModel.anime.collectAsState()
    val topAnime by trendViewModel.topAnime.collectAsState()
    val seasonLastAnime by seasonViewModel.seasonLastAnime.collectAsState()
    val seasonNow by seasonViewModel.seasonNow.collectAsState()
    val seasonUpcomingAnime by seasonViewModel.seasonUpcomingAnime.collectAsState()
    val titlePage by navigationViewModel.titlePage//Se trae la variable que guarda el titulo del juego

    Scaffold(
        scaffoldState = scaffoldState,//Se le asigna al parametro "scaffoldState", la variable recivida "scaffoldState"
        topBar = {
            TopBarNavigation(//Se llama al topbar que seleccionara uno de los dos topbar, dependiendo la situacion
                titlePage = titlePage,
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),//Se agrega un modificador
            navController = navController,
            route = Graph.HOME,
            startDestination = BottomBarNavigation.Home.route
        ) {
            composable(
                route = BottomBarNavigation.Home.route
            ) {
                HomeScreen(
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
                    allAnime = allAnime
                )
            }
            composable(
                route = Graph.DETAILS
            ) {
                val detailViewModel = hiltViewModel<DetailsViewModel>()
                AnimeDetailsScreen(//Se le asigna la funcion composable "HomeScreen"
                    navigationViewModel = navigationViewModel,
                    detailsViewModel = detailViewModel,//Se pasa el viewmodel
                    navController = navController,//Se pasa el navegador
                    onTrailerClicked = { gameUrl ->//Al parametro "onPlayTheGameClicked", se le asigna la lambda para capturar el click de abrir cuando
                        // se presiona un ir al pagina del juego como la funcion revuelve un String esta sera el LINK del juego presionado
                        uriHandler.openUri(uri = gameUrl)
                    }
                )
            }
            composable(route = BottomBarNavigation.Trend.route) {
                TrendScreen(
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
                    getTopAnime = topAnime,
                    allAnime = allAnime
                )
            }
            composable(route = BottomBarNavigation.Search.route) {
                SearchEmpty(
                    navigationViewModel = navigationViewModel
                )
            }
            composable(route = BottomBarNavigation.Season.route) {
                Tab_Navigator(
                    listAnimeLastSeason = seasonLastAnime,
                    listAnimeNowSeason = seasonNow,
                    listAnimeUpcomingSeason = seasonUpcomingAnime,
                    mainViewModel = mainViewModel,
                    navController = navController,
                    navigationViewModel = navigationViewModel,
                )
            }
            composable(route = BottomBarNavigation.Genders.route) {
                GendersScreen(

                )
            }
        }
    }
}
