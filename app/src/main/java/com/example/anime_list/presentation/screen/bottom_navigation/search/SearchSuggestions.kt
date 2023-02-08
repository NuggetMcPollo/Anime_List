package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.anime_list.R
import com.example.anime_list.domain.model.AnimeShort
import com.example.video_games.presentation.component.SearchSuggestionsItem

//Esta funcion servira para mostrar la lista de componentes de busqueda [cuando tienen un icono de buscar y aun lado el nombre]
@Composable
fun SearchSuggestions(
    query: String,//Se pasa el query a buscar
    searchResult: List<AnimeShort>,//La funcion recibe una lista de "Game", correspondiente a la lista de sugerencia que se muestra cuando se escribe una letra
    onClick: (Int) -> Unit//La funcion recibe el lambda para capturar el click
){
    val imageError = R.drawable.error_image

    if(searchResult.isEmpty()){//Se pregunta si, "searchResult" esta vacia [Quiere decir que no hay lista que mostrar]
       if(query.isNotEmpty()){//Se pregunta si "query" no esta vacia [Quiere decir que el usuario no ha escrito nada]
           ErrorMessageSearch(
               textId = R.string.Error_Anime,
               extraText = " $query",
               imageError = imageError
           )
       }
    }
    else{//Si no quiere decir, que si hay contenido
        LazyColumn(//Se llama a la funcion "LazyColumn"
            modifier = Modifier
                .padding(all = 8.dp)//Se coloca un padding, para que no este pegado a la pantalla
                .fillMaxSize(),//Se asigna un tamaño completo
            horizontalAlignment = Alignment.CenterHorizontally,//Se centra el contenido
            verticalArrangement = Arrangement.spacedBy(space = 10.dp)//Se deja un espacio entre cada item
        ){
            items(items = searchResult){ game ->//Se muestra los items
                SearchSuggestionsItem(
                    gameTitle = game.title,//Se manda la variable que mostrara el titulo
                    gameId = game.id,//Se manda la variable que mostrara el id
                    onClick = {//Al momento de presionarlo se mostrara, se captura
                        onClick(game.id)//El id del juego
                    }
                )
            }

        }
    }

}