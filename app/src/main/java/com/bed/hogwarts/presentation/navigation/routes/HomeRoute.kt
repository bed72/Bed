package com.bed.hogwarts.presentation.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import androidx.compose.runtime.LaunchedEffect

import com.bed.hogwarts.presentation.screens.Screens
import com.bed.hogwarts.presentation.screens.sale.SaleBottomSheetScreen

fun NavGraphBuilder.homeRoute(
    onDataLoaded: () -> Unit
) {
    composable(route = Screens.Home.route) {
        LaunchedEffect(key1 = null) {
            onDataLoaded()
        }
        SaleBottomSheetScreen()
//        HomeScreen {
//            it.updateState()
//        }
    }
}
