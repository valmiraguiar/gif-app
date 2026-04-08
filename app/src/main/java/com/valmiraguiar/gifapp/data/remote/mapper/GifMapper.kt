package com.valmiraguiar.gifapp.data.remote.mapper

import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.data.remote.dto.GifResponse

fun GifResponse.toModel(): Gif {
    val original = images.original

    return Gif(
        id = id,
        title = title.orEmpty(),
        username = username.orEmpty(),
        altText = altText.orEmpty(),
        pageUrl = url,
        imageUrl = original.webp?.ifBlank { original.url } ?: original.url,
        width = original.width.toIntOrNull() ?: DEFAULT_DIMENSION,
        height = original.height.toIntOrNull() ?: DEFAULT_DIMENSION
    )
}

fun List<GifResponse>.toModel(): List<Gif> = map { it.toModel() }

private const val DEFAULT_DIMENSION = 1
