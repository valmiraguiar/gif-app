package com.valmiraguiar.gifapp.presentation.home.preview

import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.presentation.home.state.HomeFilter
import com.valmiraguiar.gifapp.presentation.home.state.HomeUiState

internal object HomePreviewData {
    val uiState = HomeUiState(
        title = "GIF",
        isDarkTheme = true,
        isFavorite = true,
        isSettingsOpen = false,
        selectedFilter = HomeFilter.TRENDING
    )

    val gifs = listOf(
        Gif(
            id = "1",
            title = "Modern house cinematic loop",
            username = "giphy",
            altText = "Modern house with animated lights",
            pageUrl = "https://giphy.com",
            imageUrl = "https://media.giphy.com/media/ICOgUNjpvO0PC/giphy.gif",
            width = 480,
            height = 640
        ),
        Gif(
            id = "2",
            title = "Night city reflections",
            username = "loopstudio",
            altText = "Animated city reflections at night",
            pageUrl = "https://giphy.com",
            imageUrl = "https://media.giphy.com/media/l0HlBO7eyXzSZkJri/giphy.gif",
            width = 480,
            height = 540
        )
    )
}
