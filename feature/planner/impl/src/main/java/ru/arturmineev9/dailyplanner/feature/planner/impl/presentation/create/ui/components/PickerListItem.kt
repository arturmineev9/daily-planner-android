package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PickerListItem(
    label: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(label) },
        trailingContent = { Text(value) },
        modifier = modifier.clickable { onClick() }
    )
}
