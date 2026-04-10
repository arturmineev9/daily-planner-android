package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerEffect
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.viewmodel.PlannerViewModel

@Composable
fun PlannerRoute(
    viewModel: PlannerViewModel = hiltViewModel(),
    navigateToDetails: (Int) -> Unit,
    navigateToCreateTask: () -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is PlannerEffect.NavigateToDetails -> navigateToDetails(effect.taskId)
                is PlannerEffect.NavigateToCreateTask -> navigateToCreateTask()
            }
        }
    }

    PlannerScreen(
        state = state,
        onEvent = viewModel::handleEvent
    )
}
