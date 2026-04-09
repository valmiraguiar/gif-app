package com.valmiraguiar.gifapp.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.presentation.common.components.GlassSurface
import com.valmiraguiar.gifapp.presentation.home.components.GifFeedCard
import com.valmiraguiar.gifapp.presentation.home.components.HomeTopBar
import com.valmiraguiar.gifapp.presentation.home.components.SettingsDrawer
import com.valmiraguiar.gifapp.presentation.home.event.HomeUiEvent
import com.valmiraguiar.gifapp.presentation.home.preview.HomePreviewData
import com.valmiraguiar.gifapp.presentation.home.state.HomeUiState
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    gifs: LazyPagingItems<Gif>,
    snackbarHostState: SnackbarHostState,
    onEvent: (HomeUiEvent) -> Unit
) {
    val listState = rememberLazyListState()

    Scaffold(
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 18.dp)
            ) {
                HomeTopBar(
                    title = uiState.title,
                    onSettingsClick = { onEvent(HomeUiEvent.OpenSettingsClicked) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(18.dp))

                when (val refresh = gifs.loadState.refresh) {
                    is LoadState.Error -> ErrorState(
                        message = refresh.error.localizedMessage
                            ?: "Nao foi possivel carregar os GIFs.",
                        modifier = Modifier.fillMaxSize()
                    )

                    LoadState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                    is LoadState.NotLoading -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = listState,
                            contentPadding = PaddingValues(bottom = 36.dp),
                            verticalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            items(
                                count = gifs.itemCount,
                                key = { index -> gifs[index]?.id ?: index }
                            ) { index ->
                                gifs[index]?.let { gif ->
                                    GifFeedCard(
                                        gif = gif,
                                        isFavorite = uiState.isFavorite,
                                        onFavoriteClick = { onEvent(HomeUiEvent.FavoriteClicked) }
                                    )
                                }
                            }

                            if (gifs.loadState.append is LoadState.Loading) {
                                item {
                                    LoadingRow()
                                }
                            }
                        }
                    }
                }
            }

            SettingsDrawer(
                visible = uiState.isSettingsOpen,
                isDarkTheme = uiState.isDarkTheme,
                onDismiss = { onEvent(HomeUiEvent.CloseSettingsClicked) },
                onThemeToggle = { onEvent(HomeUiEvent.ThemeToggled(it)) }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111723)
@Composable
private fun HomeScreenPreview() {
    GIFAppTheme {
        val gifs =
            remember { flowOf(PagingData.from(HomePreviewData.gifs)) }.collectAsLazyPagingItems()

        HomeScreen(
            uiState = HomePreviewData.uiState,
            gifs = gifs,
            snackbarHostState = remember { SnackbarHostState() },
            onEvent = {}
        )
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun LoadingRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        GlassSurface(
            modifier = Modifier.padding(20.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
