package com.valmiraguiar.gifapp.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme
import com.valmiraguiar.gifapp.ui.theme.GlassBackdrop
import com.valmiraguiar.gifapp.ui.theme.GlassBorder
import com.valmiraguiar.gifapp.ui.theme.GlassHighlight

@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 28.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)

    Surface(
        modifier = modifier,
        shape = shape,
        color = GlassBackdrop,
        border = BorderStroke(1.dp, GlassBorder),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            GlassHighlight,
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.18f)
                        )
                    )
                )
                .padding(contentPadding),
            content = content
        )
    }
}

@Preview
@Composable
private fun GlassSurfacePreview() {
    GIFAppTheme {
        GlassSurface(
            modifier = Modifier.size(160.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Glass",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
