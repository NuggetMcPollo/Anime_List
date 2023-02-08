package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_list.data.repository.AnimeRepositoryImpl
import com.example.anime_list.domain.model.AnimeShort
import com.example.anime_list.util.CURRENT_ROUTE_KEY
import com.example.anime_list.util.SEARCH_SCREEN_MODE_KEY
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: AnimeRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _isLoading = Channel<Boolean>()//Se crea una variable la cual sera de tipo "Channel" y traera un boleano. Channel es una manera de comunicar valores entre corutinas
    //generalmente channel, se usa una sola vez por evento; en nuestro caso, el evento de una sola vez que queremos vigilar es el indicador de loading
    val isLoading: Flow<Boolean>
        get() = _isLoading.receiveAsFlow()

    private val _anime: MutableStateFlow<List<AnimeShort>> = MutableStateFlow(value = emptyList())
    val anime: StateFlow<List<AnimeShort>>//Se crea una variable de tipo StateFlow, pues el flujo de datos sera constante, este flujo traera siempre una lista de tipo Game, correspondiente a la consulta
    //de juegos que se esta realizando al momento
    get() = _anime//...esta obtendra el valor de "_games"

    private val _query: MutableStateFlow<String> = MutableStateFlow(value = "")
    val query: StateFlow<String>//Se crea una variable de tipo StateFlow, pues el flujo de datos sera constante, este flujo traera siempre un valor string, correspondiente a lo que se busca al momento
    //de realizar una consulta
    get() = _query//...esta obtendra el valor de "_query"

    private val _searchDetailVisible: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val searchDetailVisible: StateFlow<Boolean>//Se crea una variable de tipo StateFlow, pues el flujo de datos sera constante, este flujo traera siempre un valor booleano, correspondiente a si se
    //muestran las sugeriencias una vez que se escribe una letra o si se muestra el item que se esta buscando
    get() = _searchDetailVisible//...esta obtendra el valor de "_searchDetailVisible"

    private val _screenMode: MutableStateFlow<String> = MutableStateFlow(value = "")
    val screenMode: StateFlow<String>//Se crea una variable de tipo StateFlow, pues el flujo de datos sera constante, este flujo traera siempre un valor string, correspondiente al estado de la pantalla
    //de busqueda
    get() = _screenMode//...esta obtendra el valor de "_screenMode"

    fun clearSearchQuery(){
        _query.value = ""
    }

    fun onQuery(query: String){
        _query.value = query
    }

    fun showSearchDetail(){
        _searchDetailVisible.value = true
    }

    fun setRoute(route: String){
        savedStateHandle.set(
            key = CURRENT_ROUTE_KEY,
            value = route
        )
    }


    init {//Se coloca la logica dentro de un init, para que al momento de iniciar esta clase "GameDetailViewModel", corra todo
        savedStateHandle.get<String>(key = SEARCH_SCREEN_MODE_KEY)?.let { mode ->//Dentro de esta, se accede a la variable "savedStateHandle" y se obtendra "get" la llave
            // [La cual es de tipo String] y si no es nula se aplicara "let", como esta devuelve una lambda sera el id a guardar la cual se le pasar a la funcion "getGameDetail"
            _screenMode.value = mode //Al valor de la variable "_screenMode" se le asinga lo que tiene mode
        }
    }

    //Se crea una funcion la cual buscara los juegos dependiendo a lo que se escriba, esta sera llamada dentro de la UI
    fun onSearch(anime: List<AnimeShort>) {//La funcion recive una lista de tipo "Game"
        viewModelScope.launch {//Se coloca todo dentro un "viewModelScope", pues corre todo dentro de corrutinas
            _isLoading.send(true)//El valor de "_isLoading", se enviara a true
            delay(500)//Se asigna un retraso pequeño, porque no se quiere que asi como se escriba se busque de golpe. Se quiere un esperar un poco al usuario a que termine
            //de escribir la consulta
            _anime.value =//Al valor que tiene la variable "_games" se le asignara
                anime.filter { anime ->//Se le aplicara un filtro a la variable que tiene la lista y esta devolvera un valor
                    anime.title.contains(_query.value, ignoreCase = true)//De la lista, se fijara por el titulo y se le asignara el valor a la variable "_query"
                }
            _isLoading.send(false)//El valor de "_isLoading", se enviara a false
        }
    }

}