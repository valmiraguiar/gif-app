package com.valmiraguiar.gifapp.presentation.home.state

data class HomeUiState(
    val title: String = "GIF",
    val headline: String = "Cinematic motion, framed in glass.",
    val searchLabel: String = "Trending loops",
    val locationLabel: String = "Austin, TX",
    val isDarkTheme: Boolean = true,
    val isFavorite: Boolean = false,
    val isSettingsOpen: Boolean = false,
    val selectedFilter: HomeFilter = HomeFilter.ALL
)

enum class HomeFilter(
    val label: String
) {
    ALL("All"),
    TRENDING("Trending"),
    CURATED("Curated"),
    LOOPING("Looping")
}
