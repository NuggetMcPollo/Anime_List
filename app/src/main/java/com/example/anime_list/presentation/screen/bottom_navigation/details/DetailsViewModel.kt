package com.example.anime_list.presentation.screen.bottom_navigation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_list.data.repository.AnimeRepositoryImpl
import com.example.anime_list.domain.model.AnimeFull
import com.example.anime_list.util.ANIME_ID_KEY
import com.example.anime_list.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: AnimeRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _animeFull: MutableStateFlow<Resource<AnimeFull?>> = MutableStateFlow(value = Resource.Loading())
    val animeFull: StateFlow<Resource<AnimeFull?>>
        //Se crea una variable de tipo StateFlow, pues el flujo de datos sera constante, este flujo traera siempre una lista de "GameDetail" y...
    get() = _animeFull//...esta obtendra el valor de "_gameDetailState"

    private val _animeTitle = mutableStateOf(value = "")
    val animeTitle: State<String>
        get() = _animeTitle


    init {//Se coloca la logica dentro de un init, para que al momento de iniciar esta clase "GameDetailViewModel", corra todo
        savedStateHandle.get<String>(key = ANIME_ID_KEY)?.let { id ->//Dentro de esta, se accede a la variable "savedStateHandle" y se obtendra "get" la llave [La cual es de tipo String] y si no es nula
            //se aplicara "let", como esta devuelve una lambda sera el id a guardar la cual se le pasar a la funcion "getGameDetail"
            getAnimeDetail(animeId = id.toInt())//Se tranforma el valor a Int
        }//TODO: ESTA DEBE SER IGUAL A LA QUE QUE DECLARO "ANIME_ID_KEY" = id ----- "gameDetail/{id}" = "gameDetail/$gameId"
    }

    //Se crea una funcioon la cual obtendra los datos detallados del juego seleccionado
    private fun getAnimeDetail(animeId: Int){
        viewModelScope.launch {//Se coloca todo dentro un "viewModelScope", pues corre todo dentro de corrutinas
            _animeFull.value = repository.getAnimeById(animeId = animeId)//Se llama a la variable "_gameDetailState", a la cual se le asignara el "valor" que tiene la funcion "getAllGames()" ubicada en el "repository"
        }
    }

}