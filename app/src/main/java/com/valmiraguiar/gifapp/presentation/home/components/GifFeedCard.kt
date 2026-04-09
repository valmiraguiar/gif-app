package com.valmiraguiar.gifapp.presentation.home.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.valmiraguiar.core.domain.model.Gif
import com.valmiraguiar.gifapp.presentation.common.components.GlassSurface
import com.valmiraguiar.gifapp.presentation.home.preview.HomePreviewData
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme
import com.valmiraguiar.gifapp.ui.theme.GlassDanger
import com.valmiraguiar.gifapp.ui.theme.GlassDangerContainer

@Composable
fun GifFeedCard(
    gif: Gif,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= SDK_28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .crossfade(true)
            .respectCacheHeaders(false)
            .build()
    }
    val imageRequest = remember(context, gif.imageUrl) {
        ImageRequest.Builder(context)
            .data(gif.imageUrl)
            .crossfade(true)
            .build()
    }

    GlassSurface(
        modifier = modifier.fillMaxWidth(),
        cornerRadius = 30.dp,
        contentPadding = PaddingValues(14.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = imageRequest,
                    contentDescription = gif.altText.ifBlank { gif.title },
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(gif.aspectRatio)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .semantics {
                            contentDescription = gif.altText.ifBlank { gif.title }
                        },
                    contentScale = ContentScale.Crop
                )

                FavoriteOverlayButton(
                    isFavorite = isFavorite,
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = gif.title.ifBlank { "Untitled GIF" },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            GifInfoPill(
                label = if (gif.username.isBlank()) "by Giphy creator" else "@${gif.username}",
                accent = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun FavoriteOverlayButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = if (isFavorite) 1f else 0.94f,
        animationSpec = tween(durationMillis = TRANSITION_DURATION_TIME_IN_MILLIS),
        label = "favorite-card-scale"
    )

    GlassSurface(
        modifier = modifier
            .size(46.dp)
            .scale(scale)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription =
                    if (isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos"
            },
        cornerRadius = 999.dp
    ) {
        if (isFavorite) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(GlassDangerContainer)
            )
        }

        AnimatedContent(
            targetState = isFavorite,
            transitionSpec = {
                fadeIn(tween(TRANSITION_DURATION_TIME_IN_MILLIS)) togetherWith
                        fadeOut(tween(FADE_OUT_TIME_IN_MILLIS))
            },
            label = "favorite-card-icon",
            modifier = Modifier.align(Alignment.Center)
        ) { selected ->
            Icon(
                imageVector = if (selected) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                tint = if (selected) GlassDanger else Color.White.copy(alpha = 0.92f),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(20.dp)
            )
        }
    }
}

@Composable
private fun GifInfoPill(
    label: String,
    modifier: Modifier = Modifier,
    accent: Color
) {
    GlassSurface(
        modifier = modifier,
        cornerRadius = 18.dp,
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 10.dp
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = accent,
            fontWeight = FontWeight.Medium
        )
    }
}

private val Gif.aspectRatio: Float
    get() = width.toFloat() / height.coerceAtLeast(1).toFloat()

private const val SDK_28 = 28
private const val TRANSITION_DURATION_TIME_IN_MILLIS = 180
private const val FADE_OUT_TIME_IN_MILLIS = 120

@Preview
@Composable
private fun GifFeedCardPreview() {
    GIFAppTheme {
        GifFeedCard(
            gif = HomePreviewData.gifs.first(),
            isFavorite = true,
            onFavoriteClick = {}
        )
    }
}
