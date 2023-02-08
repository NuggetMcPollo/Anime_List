package com.example.anime_list.presentation.screen.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val icon_focused: ImageVector
) {
    object Home : BottomBarNavigation(
        route = "HOME",
        title = "Home",
        icon = Icons.Outlined.Home,
        icon_focused = Icons.Rounded.Home
    )

    object Trend : BottomBarNavigation(
        route = "TENDENCIAS",
        title = "Tendencias",
        icon = Icons.Rounded.Timeline,
        icon_focused = Icons.Outlined.Timeline
    )

    object Search : BottomBarNavigation(
        route = "BUSQUEDA",
        title = "Busqueda",
        icon = Icons.Rounded.Search,
        icon_focused = Icons.Outlined.Search
    )

    object Season : BottomBarNavigation(
        route = "TEMPORADA",
        title = "Temporada",
        icon = Icons.Outlined.Cloud,
        icon_focused = Icons.Rounded.Cloud
    )

    object Genders : BottomBarNavigation(
        route = "GENEROS",
        title = "Generos",
        icon = Icons.Outlined.Theaters,
        icon_focused = Icons.Rounded.Theaters
    )
}

