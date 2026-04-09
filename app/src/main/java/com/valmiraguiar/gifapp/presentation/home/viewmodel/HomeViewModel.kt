package com.valmiraguiar.gifapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.core.domain.usecase.GetTrendingGifsUseCase
import com.valmiraguiar.gifapp.presentation.home.event.HomeUiEvent
import com.valmiraguiar.gifapp.presentation.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTrendingGifsUseCase: GetTrendingGifsUseCase
) : ViewModel() {

    val gifs: Flow<PagingData<Gif>> = getTrendingGifsUseCase().cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<HomeUiEvent>()
    val uiEvents: SharedFlow<HomeUiEvent> = _uiEvents.asSharedFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.CloseSettingsClicked -> updateState { copy(isSettingsOpen = false) }
            HomeUiEvent.OpenSettingsClicked -> updateState { copy(isSettingsOpen = true) }
            HomeUiEvent.FavoriteClicked -> toggleFavorite()
            is HomeUiEvent.ThemeToggled -> updateState { copy(isDarkTheme = event.enabled) }
            is HomeUiEvent.FilterSelected -> updateState { copy(selectedFilter = event.filter) }
            is HomeUiEvent.ShowMessage -> Unit
        }
    }

    private fun toggleFavorite() {
        val nextValue = !_uiState.value.isFavorite
        updateState { copy(isFavorite = nextValue) }

        viewModelScope.launch {
            _uiEvents.emit(
                HomeUiEvent.ShowMessage(
                    if (nextValue) {
                        "Adicionado aos favoritos"
                    } else {
                        "Removido dos favoritos"
                    }
                )
            )
        }
    }

    private fun updateState(transform: HomeUiState.() -> HomeUiState) {
        _uiState.update(transform)
    }
}
