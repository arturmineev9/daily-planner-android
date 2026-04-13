package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerEffect
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.viewmodel.PlannerViewModel

@Composable
fun PlannerRoute(
    navigateToDetails: (Int) -> Unit,
    navigateToCreateTask: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlannerViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val currentNavigateToDetails by rememberUpdatedState(navigateToDetails)
    val currentNavigateToCreateTask by rememberUpdatedState(navigateToCreateTask)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is PlannerEffect.NavigateToDetails -> currentNavigateToDetails(effect.taskId)
                is PlannerEffect.NavigateToCreateTask -> currentNavigateToCreateTask()
            }
        }
    }

    PlannerScreen(
        state = state,
        onEvent = viewModel::handleEvent,
        modifier = modifier.fillMaxSize()
    )
}
