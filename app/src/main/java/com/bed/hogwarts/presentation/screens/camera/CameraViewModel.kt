package com.bed.hogwarts.presentation.screens.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class CameraViewModel : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun onPhotoCaptured(bitmap: Bitmap) {
        updateCapturedPhotoState(bitmap)
    }

    fun onCapturedPhotoConsumed() {
        updateCapturedPhotoState(null)
    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.image?.recycle()
        _state.value = _state.value.copy(image = updatedPhoto)
    }

    override fun onCleared() {
        _state.value.image?.recycle()
        super.onCleared()
    }
}
