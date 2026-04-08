package ru.arturmineev9.dailyplanner.core.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Event : UiEvent, Effect : UiEffect> : ViewModel() {
    protected abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> by lazy { MutableStateFlow(createInitialState()) }
    val uiState: StateFlow<State> by lazy { _uiState.asStateFlow() }

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    abstract fun handleEvent(event: Event)

    protected fun setState(reduce: State.() -> State) {
        _uiState.update { it.reduce() }
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch {
            _effect.send(effectValue)
        }
    }
}
