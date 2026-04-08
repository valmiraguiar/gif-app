package com.valmiraguiar.gifapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.core.domain.usecase.GetTrendingGifsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getTrendingGifsUseCase: GetTrendingGifsUseCase
) : ViewModel() {

    val gifs: Flow<PagingData<Gif>> = getTrendingGifsUseCase().cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
}

data class MainUiState(
    val title: String = "Trending GIFs"
)
