package com.example.anime_list.presentation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.UriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.anime_list.presentation.activity.NavGraph
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.home.HomeViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.season.SeasonViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.trend.TrendViewModel
import com.example.anime_list.presentation.screen.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun RootNavGraph(
    uriHandler: UriHandler,
    navController: NavHostController,
    mainViewModel: HomeViewModel,
    seasonViewModel: SeasonViewModel,
    trendViewModel: TrendViewModel
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        //startDestination = Graph.AUTHENTICATION
        startDestination = Graph.SPLASH
    ) {
        //authNavGraph(navController = navController)
        composable(
            route = Graph.SPLASH
        ) {
            SplashScreen(
                navController = navController,
            ){//Cuando el usuario presione atras, se quiere regresar a la screen SignIn
                navController.navigate(Graph.HOME){
                    launchSingleTop = true//Con "launchSingleTop" indicamos que solo se cree una sola instancia de esto dentro de la pila de respaldo y no multiples
                    popUpTo(route = Graph.SPLASH) {//Si se preciona el boton de atras se quiere eliminar de la pila de resplado la ruta "LoginRoutes.SignIn.name"
                        inclusive = true//Para eso se pone en true "inclusive"
                    }
                }
            }
        }
        composable(route = Graph.HOME) {
            val navigationViewModel = hiltViewModel<NavigationViewModel>()
            NavGraph(
                navigationViewModel = navigationViewModel,
                mainViewModel = mainViewModel,
                seasonViewModel = seasonViewModel,
                trendViewModel = trendViewModel,
                uriHandler = uriHandler
            )
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    //const val AUTHENTICATION = "auth_graph"
    const val HOME = "home"
    const val DETAILS = "animeDetail/{id}"
    const val SPLASH = "splash"
}