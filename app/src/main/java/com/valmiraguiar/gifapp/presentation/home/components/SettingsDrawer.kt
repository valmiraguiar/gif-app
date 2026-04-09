package com.valmiraguiar.gifapp.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valmiraguiar.gifapp.presentation.common.components.GlassSurface
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme

@Composable
fun SettingsDrawer(
    visible: Boolean,
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onThemeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(FADE_IN_ANIMATION_DURATION_IN_MILLIS)),
        exit = fadeOut(tween(FADE_OUT_ANIMATION_DURATION_IN_MILLIS)),
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.28f))
                    .clickable(onClick = onDismiss)
            )

            AnimatedVisibility(
                visible = visible,
                enter = slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(SLIDE_IN_ANIMATION_DURATION_IN_MILLIS)
                ) + fadeIn(),
                exit = slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(SLIDE_OUT_ANIMATION_DURATION_IN_MILLIS)
                ) + fadeOut(),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                GlassSurface(
                    modifier = Modifier
                        .padding(20.dp)
                        .width(300.dp)
                        .fillMaxHeight(),
                    cornerRadius = 34.dp,
                    contentPadding = PaddingValues(22.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Settings",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Glass controls and display preferences.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            GlassSurface(
                                modifier = Modifier
                                    .height(52.dp)
                                    .width(52.dp)
                                    .clip(CircleShape)
                                    .clickable(onClick = onDismiss),
                                cornerRadius = 999.dp
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "Fechar configurações",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(20.dp)
                                )
                            }
                        }

                        GlassSurface(
                            modifier = Modifier.fillMaxWidth(),
                            cornerRadius = 24.dp,
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.92f))
                                    .padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Theme",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Icon(
                                            imageVector = if (isDarkTheme) {
                                                Icons.Rounded.DarkMode
                                            } else {
                                                Icons.Rounded.LightMode
                                            },
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onSurface
                                        )

                                        Column {
                                            Text(
                                                text = if (isDarkTheme) "Dark mode" else "Light mode",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "Change theme color",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }

                                    Switch(
                                        checked = isDarkTheme,
                                        onCheckedChange = onThemeToggle
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private const val SLIDE_IN_ANIMATION_DURATION_IN_MILLIS = 320
private const val SLIDE_OUT_ANIMATION_DURATION_IN_MILLIS = 240
private const val FADE_IN_ANIMATION_DURATION_IN_MILLIS = 250
private const val FADE_OUT_ANIMATION_DURATION_IN_MILLIS = 180

@Preview(showBackground = true)
@Composable
private fun SettingsDrawerPreview() {
    GIFAppTheme {
        SettingsDrawer(
            visible = true,
            isDarkTheme = false,
            onDismiss = {},
            onThemeToggle = {}
        )
    }
}
