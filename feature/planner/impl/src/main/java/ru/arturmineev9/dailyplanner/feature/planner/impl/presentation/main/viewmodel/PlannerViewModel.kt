package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.core.ui.mvi.BaseViewModel
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTasksByDateUseCase
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerEffect
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.main.mvi.PlannerState
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.main.mapper.toUiModel
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val getTasksByDateUseCase: GetTasksByDateUseCase
) : BaseViewModel<PlannerState, PlannerEvent, PlannerEffect>() {

    private val dateTrigger = MutableStateFlow(System.currentTimeMillis())

    override fun createInitialState() = PlannerState()

    init {
        observeTasks()
    }

    private fun observeTasks() {
        dateTrigger
            .onEach { setState { copy(isLoading = true, error = null, selectedDate = it) } }
            .flatMapLatest { date -> getTasksByDateUseCase(date) }
            .onEach { result ->
                when (result) {
                    is AppResult.Success -> {
                        setState { copy(isLoading = false, hourSlots = result.data.map { it.toUiModel() }) }
                    }
                    is AppResult.Error -> {
                        setState { copy(isLoading = false, error = result.message) }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    override fun handleEvent(event: PlannerEvent) {
        when (event) {
            is PlannerEvent.OnDateSelected -> dateTrigger.value = event.timestamp
            is PlannerEvent.OnTaskClicked -> setEffect { PlannerEffect.NavigateToDetails(event.taskId) }
            is PlannerEvent.OnAddTaskClicked -> setEffect { PlannerEffect.NavigateToCreateTask }
        }
    }
}