package com.valmiraguiar.gifapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valmiraguiar.gifapp.presentation.common.components.GlassSurface
import com.valmiraguiar.gifapp.presentation.home.state.HomeFilter
import com.valmiraguiar.gifapp.ui.theme.AccentBlue
import com.valmiraguiar.gifapp.ui.theme.GIFAppTheme

@Composable
fun HomeFilterRow(
    selectedFilter: HomeFilter,
    onFilterClick: (HomeFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HomeFilter.entries.forEach { filter ->
            val isSelected = filter == selectedFilter

            GlassSurface(
                modifier = Modifier.clickable { onFilterClick(filter) },
                cornerRadius = 20.dp,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 16.dp,
                    vertical = 10.dp
                )
            ) {
                Text(
                    text = filter.label,
                    modifier = Modifier.padding(horizontal = 2.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isSelected) AccentBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeFilterRowPreview() {
    GIFAppTheme {
        HomeFilterRow(
            selectedFilter = HomeFilter.TRENDING,
            onFilterClick = {}
        )
    }
}
