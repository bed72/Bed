package com.bed.hogwarts.presentation.screens.home

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalLifecycleOwner

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    disposable: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Lifecycle(viewModel, LocalLifecycleOwner.current, disposable)

    when (uiState) {
        HomeViewModel.UiState.Loading -> TextComponent("Loading...")
        is HomeViewModel.UiState.Success ->
            TextComponent((uiState as HomeViewModel.UiState.Success).success)
        is HomeViewModel.UiState.Failure ->
            TextComponent((uiState as HomeViewModel.UiState.Failure).failure)
    }
}

@Composable
private fun TextComponent(
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = value,
        color = Color.DarkGray,
        textAlign = TextAlign.Center,
        modifier = modifier
            .size(100.dp)
            .wrapContentHeight(),
    )
}

@Composable
private fun Lifecycle(
    viewModel: HomeViewModel,
    lifecycleOwner: LifecycleOwner,
    disposable: () -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle

        lifecycle.addObserver(viewModel)

        onDispose {
            lifecycle.removeObserver(viewModel)
            disposable.invoke()
        }
    }
}