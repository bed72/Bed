package com.bed.hogwarts.presentation.screens.home

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalLifecycleOwner

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bed.core.domain.models.ProductModel

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
            ListProductsComponent((uiState as HomeViewModel.UiState.Success).success)
        is HomeViewModel.UiState.Failure ->
            TextComponent((uiState as HomeViewModel.UiState.Failure).failure)
    }
}

@Composable
@Suppress("EmptyFunctionBlock")
private fun ListProductsComponent(
    value: List<ProductModel>,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun TextComponent(
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = value,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = modifier.wrapContentWidth().wrapContentHeight()
        )
    }

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
            disposable()
        }
    }
}
