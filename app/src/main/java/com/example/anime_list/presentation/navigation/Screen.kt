package com.example.anime_list.presentation.navigation

sealed class Screen(
    val route: String
){
    object HomeScreen: Screen(route = "home")

    object AnimeDetailScreen: Screen(route = "animeDetail/{id}")


}