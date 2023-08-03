package com.bed.hogwarts.presentation.screens

sealed class Screens(val route: String) {
    data object Home : Screens("home")
}
