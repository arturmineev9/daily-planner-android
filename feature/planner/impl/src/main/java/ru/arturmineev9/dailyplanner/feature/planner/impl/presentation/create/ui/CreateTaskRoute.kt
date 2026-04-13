package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.create.mvi.CreateTaskEffect
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.create.viewmodel.CreateTaskViewModel

@Composable
fun CreateTaskRoute(
    viewModel: CreateTaskViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val currentNavigateBack by rememberUpdatedState(navigateBack)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CreateTaskEffect.NavigateBack -> currentNavigateBack()
                is CreateTaskEffect.ShowError -> {
                    snackBarHostState.showSnackbar(message = effect.message)
                }
            }
        }
    }

    CreateTaskScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onEvent = viewModel::handleEvent,
        modifier = Modifier.fillMaxSize()
    )
}
