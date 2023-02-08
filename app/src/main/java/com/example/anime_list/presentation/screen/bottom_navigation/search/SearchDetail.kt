package com.example.video_games.presentation.component

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
import com.example.anime_list.presentation.screen.bottom_navigation.search.ErrorMessageSearch
import com.example.anime_list.presentation.screen.bottom_navigation.search.SearchDetailCard


//Esta funcion servira para mostrar la lista de los componentens bien
@Composable
fun SearchDetail(
    query: String,
    searchResult: List<AnimeShort>,
    onClick: (Int) -> Unit
) {
    val imageError = R.drawable.error_image

    if(searchResult.isEmpty()){
        if(query.isNotEmpty()){
            ErrorMessageSearch(
                textId = R.string.Error_Anime,
                extraText = " $query",
                imageError = imageError
            )
        }
    }
    else{
        LazyColumn(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 10.dp)
        ){
            items(items = searchResult){ anime ->
                SearchDetailCard(
                    anime = anime,
                    onClick = {
                        onClick(anime.id)
                    }
                )
            }

        }
    }
}