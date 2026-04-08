package com.valmiraguiar.gifapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("offset") val offset: Int
)
