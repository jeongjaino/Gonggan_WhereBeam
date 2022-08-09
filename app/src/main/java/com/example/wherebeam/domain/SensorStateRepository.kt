package com.example.wherebeam.domain

import com.example.wherebeam.data.SensorData

interface SensorStateRepository {
    suspend fun getSensorState() : SensorData
}