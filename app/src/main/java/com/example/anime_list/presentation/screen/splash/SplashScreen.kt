package com.example.anime_list.presentation.screen.splash

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anime_list.R
import com.example.anime_list.presentation.theme.BackgroundScreen
import com.example.anime_list.presentation.theme.TextTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    onNavToHomePage:() -> Unit,//La funcion recibira una "unidad" para navegar a la pagina "Principal"
) {
    val imageSplash = R.drawable.nagatoro
    val titleSplash = R.string.Title_Splash
    val creator = R.string.Creator_Splash
    val background = MaterialTheme.colors.BackgroundScreen
    val letterColor = MaterialTheme.colors.TextTheme

    var startAnimation by remember { mutableStateOf(false) }//Se crea una variable para indicar el inicio de animacion

    val offsetStateText by animateDpAsState(//Se crea una variable para animar el estado de posicion
        targetValue = if (startAnimation) 0.dp else 100.dp,//Al parametro "targetValue - valor objetivo" se le asigna un bloque if y se dice si "startAnimation" es "true"
        // entonces se le asigna un valor de 0.dp de lo contrario 100.dp
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val offsetStateImage by animateDpAsState(//Se crea una variable para animar el estado de posicion
        targetValue = if (startAnimation) 0.dp else (-100).dp,//Al parametro "targetValue - valor objetivo" se le asigna un bloque if y se dice si "startAnimation" es "true"
        // entonces se le asigna un valor de 0.dp de lo contrario 100.dp
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alphaState by animateFloatAsState(//Se crea una variable para animar el estado del desvanecimiento
        targetValue = if (startAnimation) 1f else 0f,//Al parametro "targetValue - valor objetivo" se le asigna un bloque if y se dice si "startAnimation" es true entonces se le asigna un valor de 1f de lo contrario 0f
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {//Se llama a la funcion "LaunchedEffect" y se le asigna el valor de true, esto significa que para este lanzamiento el efecto activará el contenido.
        //Pero solo la primera vez que se llame al splash
        startAnimation = true//Se inicia la animacion
        delay(3000)//Se coloca un delay, para despues del tiempo
        navController.popBackStack()
        onNavToHomePage.invoke()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
    ) {
        //TODO: Body
        BodySplash(
            modifier = Modifier
                .align(Alignment.Center),
            imageSplash = imageSplash,
            titleSplash = titleSplash,
            offsetStateImage = offsetStateImage,
            offsetStateText = offsetStateText,
            alphaState = alphaState,
            letterColor = letterColor
        )
        //TODO: Footer
        FooterSplash(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            creator = creator,
            letterColor = letterColor,
            offsetStateText = offsetStateText,
            alphaState = alphaState,
        )
    }
}

/** Body */
@Composable
fun BodySplash(
    modifier: Modifier,
    @DrawableRes imageSplash: Int,
    @StringRes titleSplash: Int,
    offsetStateImage: Dp,
    offsetStateText: Dp,
    alphaState: Float,
    letterColor: Color
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(350.dp)//Se asigna un tamaño, el cual abarcara largo y ancho
                .offset(y = offsetStateImage)//Se coloca "y", porque se quiere animar de arriba hacia el centro
                .alpha(alpha = alphaState),
            painter = painterResource(id = imageSplash),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .size(10.dp)
        )
        Text(
            modifier = Modifier
                .offset(y = offsetStateText)//Se coloca "y", porque se quiere animar de arriba hacia el centro
                .alpha(alpha = alphaState),
            text = stringResource(titleSplash),
            color = letterColor,
            fontSize = 20.sp
        )
    }
}

/** Footer */
@Composable
fun FooterSplash(
    modifier: Modifier,
    @StringRes creator: Int,
    offsetStateText: Dp,
    alphaState: Float,
    letterColor: Color
) {
    Text(
        modifier = modifier
            .offset(y = offsetStateText)//Se coloca "y", porque se quiere animar de arriba hacia el centro
            .padding(end = 15.dp, bottom = 5.dp)
            .alpha(alpha = alphaState),
        text = stringResource(creator),
        color = letterColor,
        fontSize = 15.sp
    )
}