package com.example.wherebeam.data

data class SensorData(
    val temperature: Long = -1,
    val humidity: Long = -1,
    val co2: Long = -1,
    val ec: Long = -1
)
