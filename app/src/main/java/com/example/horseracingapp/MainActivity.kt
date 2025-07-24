package com.example.horseracingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.horseracingapp.presentation.MainScreen
import com.example.horseracingapp.presentation.navigation.NavGraph
import com.example.horseracingapp.ui.theme.HorseRacingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorseRacingAppTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}

