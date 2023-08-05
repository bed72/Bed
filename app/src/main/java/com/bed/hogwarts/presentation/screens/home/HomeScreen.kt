package com.bed.hogwarts.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp

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
private fun ListProductsComponent(
    values: List<ProductModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(values) {value ->
            CardComponent(value = value)
        }
    }
}

@Composable
private fun CardComponent(
    value: ProductModel,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.height(126.dp).padding(4.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextComponent(value = value.name)
            TextComponent(value = value.description)
        }
    }
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
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
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
