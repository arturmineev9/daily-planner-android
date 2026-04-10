package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskState
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.PickerListItem
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.PlannerTimePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.SaveTaskButton
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.TaskInputFields
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.PlannerDatePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.formatSelectedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    state: CreateTaskState,
    snackBarHostState: SnackbarHostState,
    onEvent: (CreateTaskEvent) -> Unit
) {
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var showStartTimePicker by rememberSaveable { mutableStateOf(false) }
    var showEndTimePicker by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Новая задача") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(CreateTaskEvent.OnBackClicked) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        bottomBar = {
            SaveTaskButton(
                isEnabled = state.isSaveButtonEnabled,
                isLoading = state.isLoading,
                onClick = { onEvent(CreateTaskEvent.OnSaveClicked) },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TaskInputFields(
                title = state.title,
                description = state.description,
                onTitleChange = { onEvent(CreateTaskEvent.OnTitleChanged(it)) },
                onDescriptionChange = { onEvent(CreateTaskEvent.OnDescriptionChanged(it)) }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            PickerListItem(
                label = "Дата",
                value = state.selectedDateMillis?.let { formatSelectedDate(it) } ?: "Выбрать",
                onClick = { showDatePicker = true }
            )

            PickerListItem(
                label = "Начало",
                value = String.format("%02d:%02d", state.startHour, state.startMinute),
                onClick = { showStartTimePicker = true }
            )

            PickerListItem(
                label = "Окончание",
                value = String.format("%02d:%02d", state.endHour, state.endMinute),
                onClick = { showEndTimePicker = true }
            )
        }
    }

    if (showDatePicker) {
        PlannerDatePicker(
            selectedDateMillis = state.selectedDateMillis ?: System.currentTimeMillis(),
            onDateSelected = { onEvent(CreateTaskEvent.OnDateSelected(it)) },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showStartTimePicker) {
        PlannerTimePicker(
            initialHour = state.startHour,
            initialMinute = state.startMinute,
            onTimeSelected = { h, m -> onEvent(CreateTaskEvent.OnStartTimeSelected(h, m)) },
            onDismiss = { showStartTimePicker = false }
        )
    }

    if (showEndTimePicker) {
        PlannerTimePicker(
            initialHour = state.endHour,
            initialMinute = state.endMinute,
            onTimeSelected = { h, m -> onEvent(CreateTaskEvent.OnEndTimeSelected(h, m)) },
            onDismiss = { showEndTimePicker = false }
        )
    }
}
