package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.details.mvi.TaskDetailEffect
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.details.viewmodel.TaskDetailViewModel

@Composable
fun TaskDetailRoute(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val currentNavigateBack by rememberUpdatedState(navigateBack)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                TaskDetailEffect.NavigateBack -> currentNavigateBack()
            }
        }
    }
    TaskDetailScreen(
        state = state,
        onEvent = viewModel::handleEvent,
        modifier = modifier.fillMaxSize()
    )
}
