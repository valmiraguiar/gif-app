package com.valmiraguiar.gifapp.presentation.home.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.paging.compose.collectAsLazyPagingItems
import com.valmiraguiar.gifapp.presentation.home.event.HomeUiEvent
import com.valmiraguiar.gifapp.presentation.home.state.HomeUiState
import com.valmiraguiar.gifapp.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    uiState: HomeUiState
) {
    val gifs = viewModel.gifs.collectAsLazyPagingItems()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.uiEvents) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is HomeUiEvent.ShowMessage -> snackbarHostState.showSnackbar(event.message)
                else -> Unit
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        gifs = gifs,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )
}
