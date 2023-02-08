package com.example.anime_list.presentation.screen.bottom_navigation.search

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessageSearch(
    @StringRes textId: Int,
    extraText: String = "",
    @DrawableRes imageError: Int,//Recibe la imagen del error
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = imageError),//Se asigna la imagen
            tint = MaterialTheme.colors.onSurface,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(//Se quiere aplicar un diferente estilo de letra a la cadena del texto, para eso
            text = buildAnnotatedString {//Al parametro "text", se le asigna el metodo "buildAnnotatedString"
                append(text = stringResource(id = textId))//Para la PRIMERA PARTE SE LE ASIGNGARA EL TEXTO
                withStyle(//Con un tipo de estilo
                    SpanStyle(fontWeight = FontWeight.Bold)
                ){
                    append(extraText)//Para la SEGUNDA PARTE SE LE ASINGARA, EL CONTENIDO RECIVIDO
                }
            },
            color = MaterialTheme.colors.onSurface,//Se asigna un color de texto
            style = MaterialTheme.typography.subtitle2//Se asigna un estilo al texto
        )
    }
}