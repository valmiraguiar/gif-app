package com.valmiraguiar.gifapp.presentation.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valmiraguiar.gifapp.presentation.home.viewmodel.HomeViewModel
import com.valmiraguiar.gifapp.presentation.navigation.GifAppNavHost
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme

@Composable
fun GifApp(viewModel: HomeViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    GIFAppTheme(darkTheme = uiState.value.isDarkTheme) {
        GifAppNavHost(
            viewModel = viewModel,
            uiState = uiState.value
        )
    }
}
