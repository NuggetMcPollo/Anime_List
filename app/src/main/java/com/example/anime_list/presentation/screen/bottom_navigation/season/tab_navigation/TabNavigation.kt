package com.example.anime_list.presentation.screen.bottom_navigation.season.tab_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabNavigation(
    val route: String,
    val icon: ImageVector,
    val title: String,
) {

    object SeasonLast : TabNavigation(
        route = "LAST_SEASON",
        icon = Icons.Outlined.Home,
        title = "Pasada",
    )

    object SeasonNow : TabNavigation(
        route = "NOW_SEASON",
        icon = Icons.Outlined.Home,
        title = "Ahora",
    )

    object SeasonUpcoming : TabNavigation(
        route = "UPCOMING_SEASON",
        icon = Icons.Outlined.Home,
        title = "Proximamente",
    )

}

