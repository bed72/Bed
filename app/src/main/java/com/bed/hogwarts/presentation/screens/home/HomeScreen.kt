package com.bed.hogwarts.presentation.screens.home

import androidx.annotation.StringRes
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.bed.core.domain.models.OffersModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    disposable: () -> Unit = {},
    onClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val (snackBarVisible, setSnackBarVisible) = remember { mutableStateOf(false) }

    Lifecycle(viewModel, LocalLifecycleOwner.current, disposable)

    if (snackBarVisible) FailureComponent((state as HomeViewModel.State.Failure).failure)

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionComponent(onClick) },
    ) { padding ->
        Surface(modifier = modifier.padding(padding)) {
            when (state) {
                HomeViewModel.State.Loading -> LoadingComponent()
                is HomeViewModel.State.Success -> ListComponent((state as HomeViewModel.State.Success).success)
                is HomeViewModel.State.Failure -> setSnackBarVisible(!snackBarVisible)
            }
        }
    }
}

@Composable
private fun FloatingActionComponent(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        text = { Text("Add Offer") },
        icon = { Icon(Icons.Filled.Add,"") },
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    )
}

@Composable
private fun LoadingComponent(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxSize()
            .wrapContentWidth()
            .wrapContentHeight(),
    )
}

@Composable
private fun FailureComponent(@StringRes message: Int, modifier: Modifier = Modifier) {
    Snackbar(
        modifier = modifier.padding(8.dp)
    ) {
        TextComponent(stringResource(id = message))
    }
}

@Composable
private fun ListComponent(
    values: List<OffersModel>,
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
    value: OffersModel,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .height(126.dp)
            .padding(4.dp)
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
