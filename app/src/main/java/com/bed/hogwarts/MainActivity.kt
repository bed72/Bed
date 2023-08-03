package com.bed.hogwarts

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.compose.rememberNavController

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.bed.hogwarts.presentation.screens.Screens
import com.bed.hogwarts.presentation.theme.HogwartsTheme
import com.bed.hogwarts.presentation.navigation.Navigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOpened
        }

        super.onCreate(savedInstanceState)
        setContent {
            HogwartsTheme(isDynamicColor = false) {
                val navigationController = rememberNavController()

                Navigation(
                    startDestination = Screens.Home.route,
                    navigationController = navigationController
                ) {
                    keepSplashOpened = false
                }
            }
        }
    }
}
