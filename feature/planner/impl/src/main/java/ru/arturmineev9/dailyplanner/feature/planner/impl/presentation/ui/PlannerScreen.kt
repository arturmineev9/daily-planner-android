package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.mvi.PlannerEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.mvi.PlannerState
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.components.DailyTimeline
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.components.PlannerDatePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.components.formatSelectedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerScreen(
    state: PlannerState,
    onEvent: (PlannerEvent) -> Unit
) {
    var isDatePickerVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ежедневник") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = { }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Surface(
                onClick = { isDatePickerVisible = true },
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
                        text = formatSelectedDate(state.selectedDate),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            // Отображение списка
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                DailyTimeline(
                    hourSlots = state.hourSlots,
                    onTaskClick = { onEvent(PlannerEvent.OnTaskClicked(it)) },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Логика показа диалога
        if (isDatePickerVisible) {
            PlannerDatePicker(
                selectedDateMillis = state.selectedDate,
                onDateSelected = { timestamp ->
                    onEvent(PlannerEvent.OnDateSelected(timestamp))
                },
                onDismiss = { isDatePickerVisible = false }
            )
        }
    }
}
