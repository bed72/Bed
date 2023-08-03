package com.bed.hogwarts.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

import com.bed.hogwarts.presentation.navigation.routes.homeRoute

@Composable
fun Navigation(
    startDestination: String,
    navigationController: NavHostController,
    onDataLoaded: () -> Unit
) {
    NavHost(
        startDestination = startDestination,
        navController = navigationController
    ) {
        homeRoute(onDataLoaded)
    }
}
