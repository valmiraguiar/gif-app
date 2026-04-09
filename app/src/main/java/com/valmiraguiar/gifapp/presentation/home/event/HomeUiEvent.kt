package com.valmiraguiar.gifapp.presentation.home.event

import com.valmiraguiar.gifapp.presentation.home.state.HomeFilter

sealed interface HomeUiEvent {
    data class ShowMessage(val message: String) : HomeUiEvent
    data object FavoriteClicked : HomeUiEvent
    data object OpenSettingsClicked : HomeUiEvent
    data object CloseSettingsClicked : HomeUiEvent
    data class ThemeToggled(val enabled: Boolean) : HomeUiEvent
    data class FilterSelected(val filter: HomeFilter) : HomeUiEvent
}
