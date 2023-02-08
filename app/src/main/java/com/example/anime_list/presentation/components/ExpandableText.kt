package com.example.anime_list.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun ExpandableText(
    text: String,//La funcion recibe el texto a colocar
    modifier: Modifier = Modifier,//La funcion recibe un modificador
    visibleLines: Int = 3,//La funcion tiene el numero de lineas visiables
    color: Color = Color.White,//La funcion recibe el color de la letra
    fontSize: TextUnit = TextUnit.Unspecified,//La funcion recibe el tamaño de la letra
    fontStyle: FontStyle? = null,//La funcion recibe el estilo de la letra
    fontWeight: FontWeight? = null,//La funcionr recibe el formato de la letra
    fontFamily: FontFamily? = null,//La funcion recibe la famila de la letra
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    buttonColor: Color = Color.White
) {

    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        }
    }
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(buildAnnotatedString { append(text) }) }

    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = buildAnnotatedString {
                    append(text)
                    withStyle(
                        SpanStyle(
                            color = buttonColor,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(" Mostrar Menos ")
                    }
                }
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(lineIndex = visibleLines - 1)
                val showMoreString = " Ver más..."
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText = buildAnnotatedString {
                    append(adjustedText)
                    append(" ")
                    withStyle(
                        SpanStyle(
                            color = buttonColor,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(showMoreString)
                    }
                }

                isClickable = true
            }
        }
    }

    Text(
        text = finalText,
        maxLines = if (isExpanded) Int.MAX_VALUE else visibleLines,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = modifier
            .clickable(enabled = isClickable) {
                isExpanded = !isExpanded
            }
            .animateContentSize(),
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        softWrap = softWrap,
        style = style,
        color = textColor
    )
}