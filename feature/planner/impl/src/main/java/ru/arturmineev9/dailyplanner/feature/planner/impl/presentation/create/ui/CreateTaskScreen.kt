package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskState
import ru.arturmineev9.dailyplanner.feature.planner.impl.R
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.PickerListItem
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.PlannerTimePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.SaveTaskButton
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui.components.TaskInputFields
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.PlannerDatePicker
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui.components.formatSelectedDate
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    state: CreateTaskState,
    snackBarHostState: SnackbarHostState,
    onEvent: (CreateTaskEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeDialog by rememberSaveable { mutableStateOf<ActiveDialog>(ActiveDialog.None) }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    ) { paddingValues ->
        CreateTaskContent(
            state = state,
            paddingValues = paddingValues,
            onEvent = onEvent,
            onOpenDialog = { activeDialog = it }
        )
    }

    CreateTaskDialogs(
        state = state,
        activeDialog = activeDialog,
        onDismiss = { activeDialog = ActiveDialog.None },
        onEvent = onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTaskTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.create_screen_topbar)) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            }
        }
    )
}

@Composable
private fun CreateTaskContent(
    state: CreateTaskState,
    paddingValues: PaddingValues,
    onEvent: (CreateTaskEvent) -> Unit,
    onOpenDialog: (ActiveDialog) -> Unit
) {
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
            label = stringResource(R.string.create_screen_date),
            value = state.selectedDateMillis?.let { formatSelectedDate(it) } ?: "Выбрать",
            onClick = { onOpenDialog(ActiveDialog.Date) }
        )

        PickerListItem(
            label = stringResource(R.string.create_screen_begin),
            value = String.format(Locale.getDefault(), "%02d:%02d", state.startHour, state.startMinute),
            onClick = { onOpenDialog(ActiveDialog.StartTime) }
        )

        PickerListItem(
            label = stringResource(R.string.create_screen_end),
            value = String.format(Locale.getDefault(), "%02d:%02d", state.endHour, state.endMinute),
            onClick = { onOpenDialog(ActiveDialog.EndTime) }
        )
    }
}

@Composable
private fun CreateTaskDialogs(
    state: CreateTaskState,
    activeDialog: ActiveDialog,
    onDismiss: () -> Unit,
    onEvent: (CreateTaskEvent) -> Unit
) {
    when (activeDialog) {
        ActiveDialog.Date -> {
            PlannerDatePicker(
                selectedDateMillis = state.selectedDateMillis ?: System.currentTimeMillis(),
                onDateSelect = { onEvent(CreateTaskEvent.OnDateSelected(it)) },
                onDismiss = onDismiss
            )
        }
        ActiveDialog.StartTime -> {
            PlannerTimePicker(
                initialHour = state.startHour,
                initialMinute = state.startMinute,
                onTimeSelect = { h, m -> onEvent(CreateTaskEvent.OnStartTimeSelected(h, m)) },
                onDismiss = onDismiss
            )
        }
        ActiveDialog.EndTime -> {
            PlannerTimePicker(
                initialHour = state.endHour,
                initialMinute = state.endMinute,
                onTimeSelect = { h, m -> onEvent(CreateTaskEvent.OnEndTimeSelected(h, m)) },
                onDismiss = onDismiss
            )
        }
        ActiveDialog.None -> { }
    }
}

private sealed interface ActiveDialog {
    data object None : ActiveDialog
    data object Date : ActiveDialog
    data object StartTime : ActiveDialog
    data object EndTime : ActiveDialog
}
