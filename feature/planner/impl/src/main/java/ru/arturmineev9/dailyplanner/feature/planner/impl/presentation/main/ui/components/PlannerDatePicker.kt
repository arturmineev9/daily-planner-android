package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlannerDatePicker(
    selectedDateMillis: Long,
    onDateSelect: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDateMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelect(datePickerState.selectedDateMillis ?: selectedDateMillis)
                onDismiss()
            }) {
                Text("ОК")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

fun formatSelectedDate(millis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}
