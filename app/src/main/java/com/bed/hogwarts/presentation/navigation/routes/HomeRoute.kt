package com.bed.hogwarts.presentation.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import androidx.compose.runtime.LaunchedEffect

import com.bed.hogwarts.presentation.screens.Screens
import com.bed.hogwarts.presentation.screens.camera.MainScreen
import com.bed.hogwarts.presentation.screens.home.HomeScreen
import com.bed.hogwarts.presentation.screens.picker.PickerScreen

fun NavGraphBuilder.homeRoute(
    onDataLoaded: () -> Unit
) {
    composable(route = Screens.Home.route) {
        LaunchedEffect(key1 = null) {
            onDataLoaded()
        }

        PickerScreen()
//        MainScreen()
//        HomeScreen {
//            it.updateState()
//        }
    }
}
