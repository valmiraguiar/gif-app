package com.valmiraguiar.gifapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.valmiraguiar.gifapp.presentation.home.screen.HomeRoute
import com.valmiraguiar.gifapp.presentation.home.state.HomeUiState
import com.valmiraguiar.gifapp.presentation.home.viewmodel.HomeViewModel

@Composable
fun GifAppNavHost(
    viewModel: HomeViewModel,
    uiState: HomeUiState
) {
    val backStack = rememberNavBackStack(HomeDestination)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryProvider = entryProvider {
            entry<HomeDestination> {
                HomeRoute(
                    viewModel = viewModel,
                    uiState = uiState
                )
            }
        }
    )
}
