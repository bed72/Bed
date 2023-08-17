package com.bed.hogwarts.presentation.screens.home

import javax.inject.Inject

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.MutableStateFlow

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver

import com.bed.core.domain.models.OffersModel
import com.bed.core.domain.models.MessageModel

import com.bed.core.usecases.sales.GetOffersUseCase

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetOffersUseCase
) : DefaultLifecycleObserver, ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)

    val state: StateFlow<State> get() = _state.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _state.value
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            useCase()
                .onStart { onLoading() }
                .collect { value ->
                    value.fold(
                        { failure -> onFailure(failure) },
                        { success -> onSuccess(success) }
                    )
                }
        }
    }

    private fun onLoading() {
        _state.update { State.Loading }
    }

    private fun onSuccess(success: List<OffersModel>) {
        _state.update { State.Success(success) }
    }

    private fun onFailure(failure: MessageModel) {
        _state.update { State.Failure(failure.message) }
    }

    sealed class State {
        data object Loading : State()
        data class Success(val success: List<OffersModel>) : State()
        data class Failure(val failure: Int) : State()
    }

}
