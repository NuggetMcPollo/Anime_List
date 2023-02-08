package com.example.anime_list.presentation.screen.bottom_navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class NavigationViewModel @Inject constructor(): ViewModel() {

    private val _isDetailsAnime = Channel<Boolean>()//Se crea una variable la cual sera de tipo "Channel" y traera un boleano. Channel es una manera de comunicar valores entre corutinas
    //generalmente channel, se usa una sola vez por evento; en nuestro caso, el evento de una sola vez que queremos vigilar es el indicador de loading
    val isDetailsAnime: Flow<Boolean>
        get() = _isDetailsAnime.receiveAsFlow()

    private val _titlePage = mutableStateOf(value = "")
    val titlePage: State<String>
        get() = _titlePage

    fun setTitlePage(title: String){
        _titlePage.value = title
    }

}