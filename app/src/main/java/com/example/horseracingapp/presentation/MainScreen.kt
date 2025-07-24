package com.example.horseracingapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.horseracingapp.R
import com.example.horseracingapp.presentation.history.HistoryScreen
import com.example.horseracingapp.presentation.race.RaceScreen

@Composable
fun MainScreen() {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF8B6F42),
                contentColor = Color(0xFFD9C1A0)
            ) {
                NavigationBarItem(
                    selected = currentRoute == "race",
                    onClick = { bottomNavController.navigate("race") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.horse),
                            contentDescription = "Race",
                            tint = Color(0xFFD9C1A0),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Скачки", color = Color(0xFFD9C1A0)) }
                )
                NavigationBarItem(
                    selected = currentRoute == "history",
                    onClick = { bottomNavController.navigate("history") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.history),
                            contentDescription = "History",
                            tint = Color(0xFFD9C1A0),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("История", color = Color(0xFFD9C1A0)) }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = "race",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("race") { RaceScreen() }
            composable("history") { HistoryScreen() }
        }
    }
}


