package com.valmiraguiar.gifapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GIFAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TrendingGifRoute(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun TrendingGifRoute(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val gifs = viewModel.gifs.collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = uiState.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = gifs.itemCount,
                key = { index -> gifs[index]?.id ?: index }
            ) { index ->
                gifs[index]?.let { gif ->
                    GifCard(gif = gif)
                }
            }

            if (gifs.loadState.append is LoadState.Loading) {
                item {
                    LoadingRow()
                }
            }
        }
    }

    when (val refresh = gifs.loadState.refresh) {
        is LoadState.Error -> ErrorState(
            message = refresh.error.localizedMessage ?: "Nao foi possivel carregar os GIFs.",
            modifier = modifier.fillMaxSize()
        )

        LoadState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is LoadState.NotLoading -> Unit
    }
}

@Preview(showBackground = true)
@Composable
fun GifCardPreview() {
    GIFAppTheme {
        GifCard(
            gif = Gif(
                id = "1",
                title = "Funny cat",
                username = "giphy",
                altText = "A cat dancing",
                pageUrl = "https://giphy.com",
                imageUrl = "https://media.giphy.com/media/ICOgUNjpvO0PC/giphy.gif"
            )
        )
    }
}

@Composable
private fun GifCard(gif: Gif) {
    Surface(
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            AsyncImage(
                model = gif.imageUrl,
                contentDescription = gif.altText.ifBlank { gif.title },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = gif.title.ifBlank { "Untitled GIF" },
                style = MaterialTheme.typography.titleMedium
            )
            if (gif.username.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "@${gif.username}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
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
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
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
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(24.dp)
        )
    }
}
