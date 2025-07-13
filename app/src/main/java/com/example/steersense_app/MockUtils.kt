package com.example.steersense_app

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

fun generateMockData(): List<String> {
    val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    val temp = (34.0..36.5).random().format(1)
    val bpm = (60..110).random()
    val gsr = (0.4..1.5).random().format(2)
    val ear = (0.15..0.35).random().format(2)
    val mor = (0.2..0.8).random().format(2)
    val tilt = (5..25).random()

    val stress = if (gsr.toDouble() > 1.0 || bpm > 90) "Yes" else "No"
    val drowsy = if (ear.toDouble() < 0.25 && mor.toDouble() > 0.6 && tilt > 15) "Yes" else "No"

    return listOf(time, temp, bpm.toString(), gsr, stress, drowsy)
}

// Extension to format double values
fun ClosedFloatingPointRange<Double>.random() = Random.nextDouble(start, endInclusive)
fun Double.format(digits: Int) = "%.${digits}f".format(this)
