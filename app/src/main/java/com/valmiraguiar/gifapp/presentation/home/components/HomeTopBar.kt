package com.valmiraguiar.gifapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valmiraguiar.gifapp.presentation.common.components.GlassSurface
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme

@Composable
fun HomeTopBar(
    title: String,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlassIconButton(
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },
                description = "Abrir configurações",
                onClick = onSettingsClick
            )
        }
    }
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    GIFAppTheme {
        HomeTopBar(
            title = "GIF",
            onSettingsClick = {}
        )
    }
}

@Composable
private fun GlassIconButton(
    icon: @Composable () -> Unit,
    description: String,
    onClick: () -> Unit
) {
    GlassSurface(
        modifier = Modifier
            .size(52.dp)
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = description
            }
    ) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            icon()
        }
    }
}
