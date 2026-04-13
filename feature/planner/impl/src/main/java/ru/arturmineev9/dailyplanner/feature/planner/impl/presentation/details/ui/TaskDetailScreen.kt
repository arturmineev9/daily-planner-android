package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi.TaskDetailEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi.TaskDetailState
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.ui.components.TaskDetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    state: TaskDetailState,
    onEvent: (TaskDetailEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val taskInfo = state.taskInfo

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Детали") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(TaskDetailEvent.OnBackClicked) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Text(
                        text = state.error ?: "Ошибка",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                taskInfo != null -> {
                    TaskDetailContent(info = taskInfo)
                }
            }
        }
    }
}
