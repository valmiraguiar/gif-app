package com.valmiraguiar.gifapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingGifListResponse(
    @SerializedName("data") val data: List<GifResponse>,
    @SerializedName("pagination") val pagination: PaginationResponse
)

data class GifResponse(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("bitly_url") val bitlyUrl: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("import_datetime") val importDatetime: String?,
    @SerializedName("alt_text") val altText: String?,
    @SerializedName("images") val images: ImageResponse
)
