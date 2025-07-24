package com.example.horseracingapp.presentation.race


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horseracingapp.R

@Composable
fun RaceScreen(viewModel: RaceViewModel = hiltViewModel()) {
    val trackColor = Color(0xFF8BC34A)
    val trackBorderColor = Color(0xFF67A617)
    val fonColor = Color(0xFFD9C1A0)
    val finishLineColor = Color.Red

    Column(
        Modifier
            .fillMaxSize()
            .background(fonColor)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "🏇 Арена Скачек",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF795548)
        )

        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(4.dp, trackBorderColor)
                .background(trackColor)
                .padding(4.dp)
        ) {
            repeat(viewModel.horses.size - 1) {
                Divider(
                    color = Color.DarkGray.copy(alpha = 0.3f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .offset(y = ((it + 1) * 75).dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .align(Alignment.CenterEnd)
                    .background(finishLineColor)
            )

            viewModel.horsePositions.forEachIndexed { index, position ->
                val horseColor = listOf(
                    Color(0xFF795548), Color.Black, Color(0xFFFFD700), Color(0xFFC0C0C0)
                ).getOrElse(index) { Color.Red }

                val horseName = viewModel.horses[index]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .offset(y = (index * 80).dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = horseName,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = (-40).dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.horse),
                        contentDescription = "Лошадь $horseName",
                        tint = horseColor,
                        modifier = Modifier
                            .size(40.dp)
                            .offset(x = position.dp)
                            .rotate(if (viewModel.raceInProgress) 15f else 0f)
                            .shadow(4.dp, CircleShape)
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        if (viewModel.raceInProgress || viewModel.winner == null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (viewModel.raceInProgress) {
                    Text(
                        "Гонка началась!",
                        color = Color(0xFF795548),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (viewModel.raceInProgress || viewModel.winner != null) {
                    Text(
                        text = "Время: ${viewModel.raceTime / 1000F} сек",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
            }
        } else {
            AnimatedVisibility(
                visible = viewModel.winner != null,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "🥇 Победитель: ${viewModel.winner}",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF388E3C)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Время: ${viewModel.raceTime / 1000F} сек",
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { viewModel.startRace() },
                enabled = !viewModel.raceInProgress,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF795548),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Старт гонки")
            }

            Spacer(Modifier.width(16.dp))

            OutlinedButton(
                onClick = { viewModel.resetRace() },
                enabled = !viewModel.raceInProgress,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF795548)
                )
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Рестарт")
            }
        }
    }
}