package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.presentation.screen.bottom_navigation.NavigationViewModel
import com.example.anime_list.util.SEARCH_MODE_KEY
import com.example.anime_list.util.navigate

@Composable
fun SearchScreen(
    onSearchButtonClick: (List<AnimeShort>) -> Unit,//La funcion recibe una lambda para capturar el juego buscado, a su vez devolvera una lista de tipo game
    navigationViewModel: NavigationViewModel,
    searchViewModel: SearchViewModel,
    navController: NavHostController,//La funcion recive el navegador
    anime: List<AnimeShort>//La funcion recive un lista de tipo "Game"
){

    navigationViewModel.setTitlePage(title = stringResource(R.string.Search))

    val screenMode by searchViewModel.screenMode.collectAsState()//Se crea una funcion para traer la variable que maneja el estado de la busqueda
    val isLoading by searchViewModel.isLoading.collectAsState(initial = false)
    val searchDetailVisible by searchViewModel.searchDetailVisible.collectAsState()
    val availableAnime by searchViewModel.anime.collectAsState()
    val searchQuery by searchViewModel.query.collectAsState()

    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize()) {//Se crea una columna a la cual se le asigna un tamaño completo
        if(screenMode == SEARCH_MODE_KEY){//Se pregunta si "screenMode" es igual a "search-mode-key"
            SearchMode(//Se llama al componente "SearchMode"
                isLoading = isLoading,//Se pasa la variable que tiene el estado de carga
                focusManager = focusManager,//Se pasa la variable que tiene el focusManager
                searchDetailVisible = searchDetailVisible,//Se le pasa la variable que verificara si se muestran las sugerencias cuando se escribe una letra o un item
                searchSuggestions = availableAnime,//Se le pasa la lista de "Game", correspondiente a la lista de sugerencia que se muestra cuando se escribe una letra
                query = searchQuery,//La funcion recibe la variable que servira para almacenar lo escrito en el textfield
                onClearQuery = {//Se le pasa los datos que hara la lambda de "onClearQuery"
                    searchViewModel.clearSearchQuery()//Se llama a la funcion de limpiar
                    navController.popBackStack()//Se pasa el navegador que de vuelta hacia atras
                },
                onSearch = { query ->//Al parametro que tiene la lambda cuando escricre, esta devolvera [La consulta escrita]
                    searchViewModel.onQuery(query = query)//Se llama a la funcion que cambia el estado de la variable y se le asigna lo que devuelve [Lo que el usuario escribrio]
                    if(query.isNotEmpty()){//Se pregunta si... [Lo que escribio el usuario, no esta vacio] hay algo
                        searchViewModel.onSearch(anime = anime)//Se llama a la funcion encargada de buscar los juegos dependiendo a lo que se escriba
                    }
                },
                search = {
                    if(searchQuery.isNotEmpty()){//Se pregunta si... la variable "searchQuery", no esta vacia [Quiere decir que el usuario escribio algo en el textfiel]
                        searchViewModel.showSearchDetail()//Se cambia el estado de mostrar las ventanas dependiendo
                    }
                },
                onItemClick = { animeId ->//A la lambda que tomara el click de juego presionado y esta como sera el id, se tomara este y se mandara a la ruta a la cual se redirigira
                    val path = "animeDetail/$animeId"//Se crea una variable la cual, tendra la RUTA DETINO A LA CUAL NEVEGARA UNA VEZ PRESIONANDO EL CLICK DEL JUEGO
                    navController.navigate(//Se le pasara el navcontroller, mas la extencion personalizada de navigate, a la cual...
                        route = path,//Se le pasara la ruta, del DESTINO AL CUAL NAVEGARA
                        onNavigate = {
                            searchViewModel.setRoute(route = path)
                        }
                    )
                },
            )
        } else{
            EmptyContent()
        }
    }
}