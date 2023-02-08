package com.example.anime_list.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessage(
    modifier: Modifier,//La funcion recibe un modificador
    @DrawableRes imageError: Int,//Recibe la imagen del error
    @StringRes messageError: Int,//Recibe el mensaje del error
    letterColor: Color//Recibe el color de la letra
){
    Column(
        modifier = modifier.fillMaxWidth(),//Se asigna un ancho completo
        horizontalAlignment = Alignment.CenterHorizontally,//Se centra el contenido horizontalmenten
        verticalArrangement = Arrangement.Center//Verticalmente se centra
    ) {
        Icon(
            modifier = Modifier
                .size(250.dp),//Se asigna un tamaño a la imagen
            painter = painterResource(id = imageError),//Se asigna la imagen
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = 8.dp)//Se deja un espacio
        )
        Text(
            text = stringResource(messageError),//Se asigna el mensaje a mostrar
            textAlign = TextAlign.Center,
            color = letterColor,//Se asigna el color de la letra
            fontSize = 20.sp,//Se asigna el tamaño
            style = MaterialTheme.typography.subtitle2//Se asigna un estilo al texto
        )
    }
}