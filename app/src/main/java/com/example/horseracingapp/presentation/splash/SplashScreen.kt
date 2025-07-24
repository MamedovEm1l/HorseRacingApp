package com.example.horseracingapp.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.horseracingapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val fullText = "Horse Racing"
    val visibleStates = remember { fullText.map { mutableStateOf(false) } }

    LaunchedEffect(Unit) {
        for (i in fullText.indices) {
            visibleStates[i].value = true
            delay(100)
        }
        delay(1000)
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9C1A0)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(R.drawable.logo),
                contentDescription = "Horse Racing Logo"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                fullText.forEachIndexed { index, char ->
                    AnimatedVisibility(
                        visible = visibleStates[index].value,
                        enter = slideInHorizontally(
                            initialOffsetX = { +40 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    ) {
                        Text(
                            text = char.toString(),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3A2D1B)
                        )
                    }
                }
            }
        }
    }
}
