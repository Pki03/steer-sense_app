package com.example.steersense_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

@Composable
fun DashboardScreen() {
    val dataList = remember { mutableStateListOf<List<String>>() }

    // Simulate live sensor data every second
    LaunchedEffect(Unit) {
        while (true) {
            val newData = generateMockData()
            dataList.add(0, newData) // Add latest data at the top
            if (dataList.size > 10) {
                dataList.removeAt(dataList.size - 1) // Keep only last 10
            }
            delay(1000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF08080))
            .padding(16.dp)
    ) {
        Text(
            "Welcome",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("Time", "Temp", "BPM", "GSR", "Stress?", "Drowsy?").forEach {
                        Text(
                            it,
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            items(dataList) { row ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEach { value ->
                        Text(
                            value,
                            modifier = Modifier.weight(1f),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
