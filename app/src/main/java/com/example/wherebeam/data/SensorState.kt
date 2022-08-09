package com.example.wherebeam.data

data class SensorState (
    val isLoading: Boolean = false,
    val sensorData: SensorData = SensorData(-1, -1, -1, -1),
    val error: String
        )