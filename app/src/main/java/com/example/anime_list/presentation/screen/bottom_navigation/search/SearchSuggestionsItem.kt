package com.example.video_games.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SearchSuggestionsItem(
    gameTitle: String,//La funcion recibe el titulo referente al juego
    gameId: Int,//La funcion recibe el id del juego
    onClick: (Int) -> Unit//La funcion recibe la lambda que capta el click y esta devuelve un valor de tipo ID referente al id del juego seleccionado [ESTE YA SE CONFIGURO ANTERIOEMNTE]
) {
    IconButton(//Se asigna un boton icon
        modifier = Modifier.fillMaxWidth(),//Se asigna un tamaño de largo completo
        onClick = {
            onClick(gameId) //Se asigna la lambda que capturara el click y este recibe el id, de tipo Int, referente al id del juego seleccionado
        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .requiredSize(size = dimensionResource(id = com.intuit.sdp.R.dimen._15sdp))
            )
            Text(
                text = gameTitle,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

}