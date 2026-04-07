package com.valmiraguiar.gifapp.framework.network.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("original") val original: ImageDataResponse
)

data class ImageDataResponse(
    @SerializedName("height") val height: String,
    @SerializedName("width") val width: String,
    @SerializedName("url") val url: String,
    @SerializedName("webp") val webp: String
)
