package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerState
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.DailyTimeline
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.PlannerDatePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.formatSelectedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(
    state: PlannerState,
    onEvent: (PlannerEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDatePickerVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            PlannerFab(onClick = { onEvent(PlannerEvent.OnAddTaskClicked) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PlannerHeader(
                selectedDate = state.selectedDate,
                onHeaderClick = { isDatePickerVisible = true }
            )

            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )

            PlannerContent(
                state = state,
                onTaskClick = { onEvent(PlannerEvent.OnTaskClicked(it)) }
            )
        }

        if (isDatePickerVisible) {
            PlannerDatePicker(
                selectedDateMillis = state.selectedDate,
                onDateSelect = { onEvent(PlannerEvent.OnDateSelected(it)) },
                onDismiss = { isDatePickerVisible = false }
            )
        }
    }
}

@Composable
private fun PlannerFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Добавить задачу"
        )
    }
}

@Composable
private fun PlannerHeader(
    selectedDate: Long,
    onHeaderClick: () -> Unit
) {
    Surface(
        onClick = onHeaderClick,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Выбранный день:",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = formatSelectedDate(selectedDate),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun PlannerContent(
    state: PlannerState,
    onTaskClick: (Int) -> Unit
) {
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        DailyTimeline(
            selectedDate = state.selectedDate,
            hourSlots = state.hourSlots,
            onTaskClick = onTaskClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}
