package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.anime_list.R

@Composable
fun SearchBar(
    query: String,
    focusManager: FocusManager,
    onSearch: (String) -> Unit,
    search: () -> Unit,
    onClearQuery: () -> Unit
){
    Surface(//Se declara una superficie
        elevation = 8.dp,//Se le asigna una elevacion
        modifier = Modifier
            .fillMaxWidth()//Se asigna un ancho completo
            .requiredHeight(75.dp)//Se asigna un largo
    ) {
        Row(//Se declara una fila
            modifier = Modifier//Se declara un modificador
                .padding(start = 8.dp, end = 8.dp, top = 3.dp)//Se asinga un padding al contenido dentro de la fila
                .fillMaxSize(),//Se asigna un tamaño completo
            horizontalArrangement = Arrangement.SpaceBetween,//Se asigna que el contenido tenga espacio
            verticalAlignment = Alignment.CenterVertically//Se centra el contenido
        ) {
             TextField(//Se asigna un "TextField"
                 label = {//Se coloca un label
                     Text(//Se coloca un texto
                         text = stringResource(id = R.string.Search),//Se asigna un texto dentro
                         color = MaterialTheme.colors.onBackground//Se asigna el color
                     )
                 },
                 modifier = Modifier.fillMaxWidth(fraction = 0.89f),//Se asigna cuanto ocupara el textfiled
                 value = query,//Al valor actual, se le asinga
                 textStyle = MaterialTheme.typography.subtitle2,//Se asigna el estilo de la letra
                 onValueChange = onSearch,//Se asigna el valor cambiante
                 keyboardOptions = KeyboardOptions(//Se asigna el estilo del teclado
                     keyboardType = KeyboardType.Text,
                     imeAction = ImeAction.Search//Se asigna que se a de busqueda
                 ),
                 keyboardActions = KeyboardActions(onSearch = {//Cuando se precione el boton de busqueda
                       focusManager.clearFocus()//Se limpia el enfoque
                       search()//Se manda la lambda buscar
                 }),
                 shape = MaterialTheme.shapes.small
             )
            IconButton(onClick = onClearQuery) {//Cuando se presione el boton de X, se llama a la funcion "onClearQuery"
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.requiredSize(size = dimensionResource(id = com.intuit.sdp.R.dimen._24sdp))
                )
            }
        }

    }
}