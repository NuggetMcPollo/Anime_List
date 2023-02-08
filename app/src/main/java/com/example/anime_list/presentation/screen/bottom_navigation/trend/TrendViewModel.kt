package com.example.anime_list.presentation.screen.bottom_navigation.trend

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_list.data.repository.AnimeRepositoryImpl
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    private val repository: AnimeRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _topAnime: MutableStateFlow<Resource<List<AnimeShort>>> = MutableStateFlow(value = Resource.Loading())
    val topAnime: StateFlow<Resource<List<AnimeShort>>>
        //Se crea una variable de tipo StateFlow, pues el flujo de datos sera constante, este flujo traera siempre una lista de "anime" y...
        get() = _topAnime//...esta obtendra el valor de "_anime"

    private val _currentRoute: MutableStateFlow<String> = MutableStateFlow(value = savedStateHandle.get(key = "current-route-key") ?: "")
    val currentRoute: StateFlow<String>
        get() = _currentRoute

    init {//Se coloca la logica dentro de un init, para que al momento de iniciar esta clase "MainViewModel", corra todo
        viewModelScope.launch {//Se coloca todo dentro un "viewModelScope", pues corre todo dentro de corrutinas
            _topAnime.value = repository.getTrendAnime()//Se llama a la variable "_games", a la cual se le asignara el "valor" que tiene la funcion "getAllGames()" ubicada en el "repository"
        }
    }

}