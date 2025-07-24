package com.example.horseracingapp.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    val textColor = Color(0xFFEED6B0)
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFD9C1A0))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📜 История Скачек",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3A2D1B)
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { viewModel.clearHistory() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8B6F42),
                contentColor = textColor
            )
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Очистить историю",
                tint = textColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Очистить историю")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(viewModel.history) { result ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF8B6F42)) // закраска карточки
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = "🏁 Победитель: ${result.winner}",
                            fontWeight = FontWeight.Medium,
                            color = textColor
                        )
                        Text(
                            text = "⏱ Время: ${result.duration} мс",
                            color = textColor
                        )
                        Text(
                            text = "📅 ${
                                SimpleDateFormat(
                                    "dd MMM yyyy, HH:mm",
                                    Locale.getDefault()
                                ).format(Date(result.timestamp))
                            }",
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}
