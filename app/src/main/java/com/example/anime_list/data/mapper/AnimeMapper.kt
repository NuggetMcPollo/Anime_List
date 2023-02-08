package com.example.anime_list.data.mapper

import com.example.anime_list.data.remote.dto.AnimeFullDto
import com.example.anime_list.data.remote.dto.AnimeShortDto
import com.example.anime_list.data.remote.dto.AnimeGenresDto
import com.example.anime_list.domain.model.AnimeFull
import com.example.anime_list.domain.model.AnimeGenres
import com.example.anime_list.domain.model.AnimeShort

fun AnimeShortDto.toDomain(): AnimeShort{//Se crea una "EXTENCION" de la data class "CharacterDataDto" [SON LOS DATOS COMPLETOS] y esta devolvera un valor de tipo "DisneyCharacter" [SON LOS DATOS A MOSTRAR]
    // sera de tipo nulleable, por si regresa un caso comentado abajo en verde.
    return AnimeShort(//Regresara un valor de tipo "DisneyCharacterDagger", la cual se le asigna en el...
        id = this.id,
        images = this.images,
        title = this.title,
        score = this.score,
        airing = this.airing
    )
}

fun AnimeFullDto.toDomain(): AnimeFull{//Se crea una "EXTENCION" de la data class "CharacterDataDto" [SON LOS DATOS COMPLETOS] y esta devolvera un valor de tipo "DisneyCharacter" [SON LOS DATOS A MOSTRAR]
    // sera de tipo nulleable, por si regresa un caso comentado abajo en verde.
    return AnimeFull(
        id = this.id,
        images = this.images,
        title = this.title,
        title_english = this.title_english,
        title_japanese = this.title_japanese,
        score = this.score,
        airing = this.airing,
        release = this.release,
        synopsis = this.synopsis,
        episodes = this.episodes,
        duration = this.duration,
        genres = this.genres,
        streaming = this.streaming,
        trailer = this.trailer,
    )
}

fun AnimeGenresDto.toDomain(): AnimeGenres{
    return  AnimeGenres(
        id = this.id,
        name = this.name
    )
}
