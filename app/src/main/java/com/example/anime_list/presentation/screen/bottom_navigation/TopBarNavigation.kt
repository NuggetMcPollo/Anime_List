package com.example.anime_list.presentation.screen.bottom_navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.anime_list.presentation.theme.Orange

@Composable
fun TopBarNavigation(
    titlePage: String
) {

    TopAppBar(
        backgroundColor = Orange,//Se asigna el background
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = titlePage,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    )

}