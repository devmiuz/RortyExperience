package uz.devmi.rortyexperience.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, ACTION, EFFECT>(
    private val initialState: STATE
) : ViewModel() {

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effects by lazy { Channel<EFFECT>() }
    val effects: Flow<EFFECT> by lazy { _effects.receiveAsFlow() }


    abstract fun onAction(action: ACTION)


    fun resetState() {
        _state.tryEmit(initialState)
    }

    fun setState(newState: STATE) {
        _state.tryEmit(newState)
    }

    fun updateState(producer: (STATE) -> STATE) {
        _state.update { producer(it) }
    }

    suspend fun emitEffect(sideEffect: EFFECT) {
        _effects.send(sideEffect)
    }

    fun tryEmitEffect(sideEffect: EFFECT) {
        _effects.trySend(sideEffect)
    }

    fun <T, S : DisplayState<T>, D> handleApiCall(
        task: suspend () -> Either<AppError, T>,
        onModify: (DisplayState<D>) -> Unit,
        produceData: (T) -> D = { it as D },
        onSuccess: suspend (T) -> Unit = {},
        onFailure: suspend (AppError) -> Unit = {}
    ) {
        viewModelScope.launch {
            onModify(DisplayState(isLoading = true))
            when (val result = task()) {
                is Either.Right -> {
                    onModify(DisplayState(data = produceData(result.value)))
                    onSuccess(result.value)
                }

                is Either.Left -> {
                    onModify(DisplayState(error = result.value))
                    onFailure(result.value)
                }
            }
        }
    }
}