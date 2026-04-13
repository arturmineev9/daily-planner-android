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
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.PlannerDatePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.formatSelectedDate
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.ui.create.components.TaskInputFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    state: CreateTaskState,
    snackBarHostState: SnackbarHostState,
    onEvent: (CreateTaskEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var showStartTimePicker by rememberSaveable { mutableStateOf(false) }
    var showEndTimePicker by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            CreateTaskTopBar(onBackClick = { onEvent(CreateTaskEvent.OnBackClicked) })
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
        CreateTaskContent(
            state = state,
            paddingValues = paddingValues,
            onEvent = onEvent,
            onDateClick = { showDatePicker = true },
            onStartTimeClick = { showStartTimePicker = true },
            onEndTimeClick = { showEndTimePicker = true }
        )
    }


    CreateTaskDialogs(
        state = state,
        showDatePicker = showDatePicker,
        showStartTimePicker = showStartTimePicker,
        showEndTimePicker = showEndTimePicker,
        onDateDismiss = { showDatePicker = false },
        onStartTimeDismiss = { showStartTimePicker = false },
        onEndTimeDismiss = { showEndTimePicker = false },
        onEvent = onEvent
    )
}
