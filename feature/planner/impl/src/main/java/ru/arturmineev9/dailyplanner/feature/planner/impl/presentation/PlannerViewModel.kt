package ru.arturmineev9.dailyplanner.feature.planner.impl.presentation

import androidx.lifecycle.viewModelScope
import ru.arturmineev9.dailyplanner.feature.planner.api.domain.usecase.GetTasksByDateUseCase
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.arturmineev9.dailyplanner.core.common.result.AppResult
import ru.arturmineev9.dailyplanner.core.ui.mvi.BaseViewModel
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.mvi.PlannerEffect
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.mvi.PlannerEvent
import ru.arturmineev9.dailyplanner.feature.planner.api.presentation.mvi.PlannerState
import ru.arturmineev9.dailyplanner.feature.planner.impl.presentation.mapper.toUiModel
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
            PlannerEvent.OnAddTaskClicked -> setEffect { PlannerEffect.NavigateToCreateTask }
        }
    }
}
