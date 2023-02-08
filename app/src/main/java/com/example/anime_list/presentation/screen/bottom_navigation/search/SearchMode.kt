package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.example.anime_list.domain.model.AnimeShort

import com.example.video_games.presentation.component.SearchDetail

//Esta funcion sera la responsable de la busqueda de juegos
@Composable
fun SearchMode(
    isLoading: Boolean,//La funcion recivira un valor de tipo Boolean, referente a cuando esta cargando
    searchSuggestions: List<AnimeShort>,//La funcion recivira una lista de "Game", correspondiente a la lista de sugerencia que se muestra cuando se escribe una letra
    focusManager: FocusManager,
    onItemClick: (Int) -> Unit,
    onClearQuery: () -> Unit,
    onSearch: (String) -> Unit,
    search: () -> Unit,
    query: String,
    searchDetailVisible: Boolean
){

    SearchBar(//Se llama al componente "SearchBar"
        query = query,//Se le pasa la "query"
        focusManager = focusManager,//Se le pasa el "focusManager"
        onClearQuery = onClearQuery,//Se pasa el "onClearQuery"
        onSearch = onSearch,//Se pasa el "onSearch"
        search = search//Se pasa la buqueda
    )

    if(isLoading){//Se pregunta, si el estado esta en carga, se mostrara un...
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())//Progress bar
    } else{//Si no quiere decir que ya esta bien todo
       Spacer(modifier = Modifier.padding(vertical = 16.dp))//Se asigna un espacio
       if(!searchDetailVisible){//Se pregunta si "searchDetailVisible" es true. Quiere decir que se estan mostrando, las sugerencias cuando se escribe una letra
           SearchSuggestions(//Se llamara al componente que muestra las sugerencias a buscar
               query = query,//Se pasa la busqueda
               searchResult = searchSuggestions,//Se le pasa la lista de "Game", correspondiente a la lista de sugerencia que se muestra cuando se escribe una letra
               onClick =  {
                   onItemClick(it) //Se pasa la lambda que capturara el click
               }
           )
       } else{//Si esta en false quiere decir que se esta mostrando los items a detalle con la busqueda
           SearchDetail(//Se llamara al componente que muestra las sugerencias a detalle
               query = query,//Se pasa la busqueda
               searchResult = searchSuggestions,//Se pasan los resultados
               onClick = {
                   onItemClick(it) //Se pasa la lambda que capturara el click
               }
           )
       }
    }
}
