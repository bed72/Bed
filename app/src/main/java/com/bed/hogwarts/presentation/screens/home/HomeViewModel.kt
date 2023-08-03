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

import com.bed.core.usecases.products.GetProductUseCase

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetProductUseCase
) : DefaultLifecycleObserver, ViewModel() {


    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            useCase()
                .onStart { onLoading() }
                .collect { value ->
                    value.fold(
                        { failure -> onFailure(failure.error) },
                        { success -> onSuccess(success[0].name) }
                    )
                }
        }
    }

    private fun onLoading() {
        _uiState.update { UiState.Loading }
    }

    private fun onSuccess(success: String) {
        _uiState.update { UiState.Success(success) }
    }

    private fun onFailure(failure: String) {
        _uiState.update { UiState.Failure(failure) }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val success: String) : UiState()
        data class Failure(val failure: String) : UiState()
    }

}