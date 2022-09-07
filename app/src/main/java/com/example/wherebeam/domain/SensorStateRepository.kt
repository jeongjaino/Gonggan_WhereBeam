package com.example.wherebeam.domain

import com.example.wherebeam.data.SensorData
import kotlinx.coroutines.flow.Flow

interface SensorStateRepository {
    suspend fun getSensorState() : Flow<SensorData?>
}