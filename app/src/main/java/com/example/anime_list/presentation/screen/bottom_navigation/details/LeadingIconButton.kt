package com.example.anime_list.presentation.screen.bottom_navigation.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.anime_list.presentation.theme.Orange

@Composable
fun LeadingIconButton(
    textButton: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    enabled: Boolean = true,
    iconTrailer: ImageVector,//La funcion recibira un icon en caso de fallar la carga
    border: BorderStroke? = null,
    contentDescription: String = "",
){
    Button(
        onClick = onClick,
        border = border,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange,
            contentColor = Color.White
        )
    ){
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .requiredSize(iconSize),
                imageVector = iconTrailer,
                tint = Color.White,
                contentDescription = contentDescription
            )
            Text(
                text = textButton,
                color = Color.White,
                modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
            )

        }
    }

}