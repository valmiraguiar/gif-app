package com.valmiraguiar.core.data.network.response

data class RandomGifListResponse (
    val data: List<GifResponse>
)

data class GifResponse(
    val type: String,
    val id: String,
    val url: String,
    val bitly_url: String,
    val username: String,
    val title: String,
    val import_datetime: String,
    val alt_text: String,
    val images: ImageResponse
)