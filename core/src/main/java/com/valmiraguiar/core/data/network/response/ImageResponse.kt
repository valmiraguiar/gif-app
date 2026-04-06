package com.valmiraguiar.core.data.network.response

data class ImageResponse(
    val original: ImageDataResponse
)

data class ImageDataResponse(
    val height: String,
    val width: String,
    val url: String,
    val webp: String
)