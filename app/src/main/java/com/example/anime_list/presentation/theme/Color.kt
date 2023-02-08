package com.example.anime_list.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Mirage = Color(0xFF1D2335)
val MirageDark = Color(0xFF181D2D)
val Orange = Color(0xffff3b2f)
val Star = Color(0xFFFFE500)

val IndicatorPager = Color(0x80004988)

val brightBlue = Color(0xFF027cf5)

//Se crea una extencion de "Colors" dentro de una variable, y se accedera a esta extencion por el nombre de "BackgroundScreen"
val Colors.BackgroundScreen
    get() =
        if (isLight) Color.White//Si esta usando el tema de dia, el color de "BackgroundScreen", sera blanco
        else Mirage//De lo contrario sera Mirage

//Se crea una extencion de "Colors" dentro de una variable, y se accedera a esta extencion por el nombre de "BackgroundScreen"
val Colors.BackgroundCard
    get() =
        if (isLight) Color.White//Si esta usando el tema de dia, el color de "BackgroundScreen", sera blanco
        else MirageDark//De lo contrario sera Mirage

//Se crea una extencion de "Colors" dentro de una variable, y se accedera a esta extencion por el nombre de "TextTheme"
val Colors.TextTheme
    get() =
        if (isLight) Color.Black//Si esta usando el tema de dia, el color de "TextTheme", sera Negro
        else Color.White//De lo contrario sera Blanco