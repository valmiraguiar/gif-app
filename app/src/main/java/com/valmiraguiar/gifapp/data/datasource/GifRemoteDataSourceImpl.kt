@file:Suppress("LongMethod, MaximumLineLength, UnusedPrivateProperty")

package com.valmiraguiar.gifapp.data.datasource

import com.valmiraguiar.gifapp.data.remote.api.GifApi
import com.valmiraguiar.gifapp.data.remote.dto.GifResponse
import com.valmiraguiar.gifapp.data.remote.dto.ImageDataResponse
import com.valmiraguiar.gifapp.data.remote.dto.ImageResponse
import com.valmiraguiar.gifapp.data.remote.dto.PaginationResponse
import com.valmiraguiar.gifapp.data.remote.dto.TrendingGifListResponse
import javax.inject.Inject

class GifRemoteDataSourceImpl @Inject constructor(
    private val api: GifApi
) : GifRemoteDataSource {
    override suspend fun getTrendingGifs(
        limit: Int,
        offset: Int
    ): TrendingGifListResponse = TrendingGifListResponse(
        data = listOf(
            GifResponse(
                type = "gif",
                id = "G6N0pDDgDpLjUvNoyQ",
                url = "https://giphy.com/gifs/kiss-couple-cheek-G6N0pDDgDpLjUvNoyQ",
                bitlyUrl = "https://gph.is/g/4VP7wVe",
                username = "jersey_couple",
                title = "Kissing Kiss Me GIF by jerseycouple",
                importDatetime = "2023-05-29 09:45:13",
                altText = "",
                images = ImageResponse(
                    original = ImageDataResponse(
                        height = "270",
                        width = "310",
                        url = "https://media4.giphy.com/media/G6N0pDDgDpLjUvNoyQ/giphy.gif",
                        webp = "https://media4.giphy.com/media/G6N0pDDgDpLjUvNoyQ/giphy.webp"
                    )
                )
            ),
            GifResponse(
                type = "gif",
                id = "6dbh3QJ5ZE9zF4LtOb",
                url = "https://giphy.com/gifs/rvappstudios-wednesday-happy-wonderful-6dbh3QJ5ZE9zF4LtOb",
                bitlyUrl = "https://gph.is/g/4gbnG77",
                username = "rvappstudios",
                title = "Wednesday Morning GIF by Lucas and Friends by RV AppStudios",
                importDatetime = "2024-05-14 14:36:26",
                altText = "",
                images = ImageResponse(
                    original = ImageDataResponse(
                        height = "340",
                        width = "480",
                        url = "https://media2.giphy.com/media/6dbh3QJ5ZE9zF4LtOb/giphy.gif",
                        webp = "https://media2.giphy.com/media/6dbh3QJ5ZE9zF4LtOb/giphy.webp"
                    )
                )
            ),
            GifResponse(
                type = "gif",
                id = "td5eq6qlu1UL6",
                url = "https://giphy.com/gifs/dance-happy-happyness-td5eq6qlu1UL6",
                bitlyUrl = "http://gph.is/1ksHvjX",
                username = "",
                title = "happy animation GIF",
                importDatetime = "2014-03-03 23:23:08",
                altText = "",
                images = ImageResponse(
                    original = ImageDataResponse(
                        height = "419",
                        width = "365",
                        url = "https://media1.giphy.com/media/td5eq6qlu1UL6/giphy.gif",
                        webp = "https://media1.giphy.com/media/td5eq6qlu1UL6/giphy.webp"
                    )
                )
            ),
            GifResponse(
                type = "gif",
                id = "118p3q768COZhu",
                url = "https://giphy.com/gifs/funny-fun-118p3q768COZhu",
                bitlyUrl = "http://gph.is/13BF59g",
                username = "",
                title = "Confused Girl GIF",
                importDatetime = "2013-09-09 14:20:24",
                altText = "Video gif. A young blonde haired girl in high pigtails drops her chin in confusion as she twists her head with her tiny palms up in wonder.",
                images = ImageResponse(
                    original = ImageDataResponse(
                        height = "182",
                        width = "400",
                        url = "https://media3.giphy.com/media/118p3q768COZhu/giphy.gif",
                        webp = "https://media3.giphy.com/media/118p3q768COZhu/giphy.webp"
                    )
                )
            ),
            GifResponse(
                type = "gif",
                id = "UlTZOUi5vv5tbMYct3",
                url = "https://giphy.com/gifs/mango-mustard-ernkoch-UlTZOUi5vv5tbMYct3",
                bitlyUrl = "https://gph.is/g/4ogynRm",
                username = "ernkoch",
                title = "Sigma Mango GIF",
                importDatetime = "2025-12-11 15:35:54",
                altText = "",
                images = ImageResponse(
                    original = ImageDataResponse(
                        height = "264",
                        width = "480",
                        url = "https://media1.giphy.com/media/UlTZOUi5vv5tbMYct3/giphy.gif",
                        webp = "https://media1.giphy.com/media/UlTZOUi5vv5tbMYct3/giphy.webp"
                    )
                )
            )
        ),
        pagination = PaginationResponse(
            totalCount = 5,
            count = 5,
            offset = 0
        )
    ) // api.getTrendingGifs(limit, offset)
    // TODO - Remove mock
}
