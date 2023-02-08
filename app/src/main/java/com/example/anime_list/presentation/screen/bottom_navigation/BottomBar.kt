package com.example.anime_list.presentation.screen.bottom_navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.anime_list.presentation.theme.MirageDark
import com.example.anime_list.presentation.theme.Orange

@Composable
fun BottomBar(//Se crea la funcion del BottomBar
    navController: NavHostController//Recivira un navcontroller
) {
    val screens = listOf(
        //Se crea una variable que tiene la lista de las screen
        BottomBarNavigation.Home,
        BottomBarNavigation.Trend,
        BottomBarNavigation.Search,
        BottomBarNavigation.Season,
        BottomBarNavigation.Genders,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()//Se crea una variable la cual contiene el estado del nav
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any {
        it.route == currentDestination?.route
    }

    Row(
        //Esta fila, funciona como la PRINCIPAL; Esta contendra TODOS LOS ITEMS
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.White))//Se asigna un tamaño de borde con un color
            .background(Orange)
            .fillMaxWidth(),//Se asigna un ancho completo
        verticalAlignment = Alignment.CenterVertically,//Se centra el contenido que tendra la fila PRINCIPAL
    ) {
        if (bottomBarDestination) {
            BottomNavigation {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarNavigation,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val backgroundIcon = //Se crea una variable que contendra el fondo de un icono
        if (selected)//Si esta seleccionado
            MirageDark //Se coloca un fondo Blanco
        else //Si no, lo esta
            Color.Transparent //Se coloca un fondo transparente

    val contentIcon = //Se crea una variable que contendra el contenido de un icono
        if (selected) //Si esta seleccionado
            Color.White //El contenido sera Blanco
        else //Si no, lo esta
            Color.Black//El contenido sera Negro

    Row( //Se crea una fila que alvergara CADA icono dentro del Bottom-Nav
        modifier = Modifier
            //.border(BorderStroke(1.dp, Color.White))//Se asigna un color y un tamaño de borde
            .background(Orange),//Se asigna un fondo
        verticalAlignment = Alignment.CenterVertically,//Se centra el contenido que tendra la fila PRINCIPAL
    ) {
        Box(//Esta caja reprentrara el contenido de un icon
            modifier = Modifier
                .background(backgroundIcon)//Color del fondo de cuando se selecciona un item
                .width(85.dp)
                .height(80.dp)
                .clickable(onClick = {//Cuando se presione un icono
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }),
            contentAlignment = Alignment.Center//Tod0 el contenido de la caja se centra
        ) {
            Column(//Dentro de cada icono tendra una columna
                modifier = Modifier
                    .padding(
                        vertical = 5.dp
                        //start = 10.dp,
                        //end = 10.dp,
                        //top = 8.dp,
                        //bottom = 8.dp
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = (
                            if (selected) screen.icon_focused
                            else screen.icon
                            ),
                    contentDescription = "icon",
                    tint = contentIcon
                )
                /*Spacer(
                    modifier = Modifier.size(5.dp)
                )
                AnimatedVisibility(visible = selected) {
                    Text(
                        text = screen.title,
                        fontSize = 13.sp,
                        color = contentIcon
                    )
                }*/
            }
        }
    }
}
