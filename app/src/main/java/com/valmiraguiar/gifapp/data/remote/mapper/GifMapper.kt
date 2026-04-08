package com.valmiraguiar.gifapp.data.remote.mapper

import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.data.remote.dto.GifResponse

fun GifResponse.toModel(): Gif {
    return Gif(
        id = id,
        title = title.orEmpty(),
        username = username.orEmpty(),
        altText = altText.orEmpty(),
        pageUrl = url,
        imageUrl = images.original.webp?.ifBlank { images.original.url } ?: images.original.url
    )
}

fun List<GifResponse>.toModel(): List<Gif> = map { it.toModel() }
