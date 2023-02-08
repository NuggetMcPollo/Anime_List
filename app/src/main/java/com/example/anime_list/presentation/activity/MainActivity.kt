package com.example.anime_list.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.compose.rememberNavController
import com.example.anime_list.presentation.graphs.RootNavGraph
import com.example.anime_list.presentation.screen.bottom_navigation.home.HomeViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.season.SeasonViewModel
import com.example.anime_list.presentation.screen.bottom_navigation.trend.TrendViewModel
import com.example.anime_list.presentation.theme.Anime_ListTheme
import com.example.anime_list.presentation.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint//Con la anotacion "@AndroidEntryPoint" se indica que aqui se inyecta las dependencias de una clase padre, y en este caso la clase padre SIEMPRE sera la MyApp.
/** Si uno tiene muchas activitys, la anotacion debe de ir en todas ellas, basicamente en los componentes android. OJO: si tiene un broadcast resiver o un service tambien iria la anotacion */
class MainActivity : ComponentActivity() {

    private val mainViewModel: HomeViewModel by viewModels()//Se crea una variable la cual heredara de "MainViewModel" y declinara de "viewModels()"
    private val trendViewModel: TrendViewModel by viewModels()//Se crea una variable la cual heredara de "MainViewModel" y declinara de "viewModels()"
    private val seasonViewModel: SeasonViewModel by viewModels()//Se crea una variable la cual heredara de "MainViewModel" y declinara de "viewModels()"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uriHandler = LocalUriHandler.current//Se crea una variable que manejara el URL del juego seleccionado
            Anime_ListTheme{
                val systemUiController = rememberSystemUiController()//Se crea una variable que recuerde el ui del systema, HACER LA IMPORTACION
                if (isSystemInDarkTheme()) {// Quiere decir que el tema oscuro esta ENCENDIDO
                    systemUiController.setSystemBarsColor(
                        color = Orange
                    )
                    systemUiController.setNavigationBarColor(
                        color = Orange
                    )
                }else{// Quiere decir que el tema oscuro esta APAGADO
                    systemUiController.setSystemBarsColor(
                        color = Orange
                    )
                    systemUiController.setNavigationBarColor(
                        color = Orange
                    )
                }
                RootNavGraph(
                    navController = rememberNavController(),
                    mainViewModel = mainViewModel,
                    trendViewModel = trendViewModel,
                    seasonViewModel = seasonViewModel,
                    uriHandler = uriHandler
                )
            }
        }
    }
}