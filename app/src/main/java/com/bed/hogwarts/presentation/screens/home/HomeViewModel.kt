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
                        { failure -> onFailure(failure) },
                        { success -> onSuccess(success) }
                    )
                }
        }
    }

    private fun onLoading() {
        _uiState.update { UiState.Loading }
    }

    private fun onSuccess(success: List<OffersModel>) {
        _uiState.update { UiState.Success(success) }
    }

    private fun onFailure(failure: MessageModel) {
        _uiState.update { UiState.Failure(failure.message) }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val success: List<OffersModel>) : UiState()
        data class Failure(val failure: Int) : UiState()
    }

}
