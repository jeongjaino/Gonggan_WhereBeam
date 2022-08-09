package com.example.wherebeam.data

import com.example.wherebeam.utils.FirestoreProvider
import com.example.wherebeam.domain.SensorStateRepository

class SensorStateRepositoryImpl: SensorStateRepository {

    override suspend fun getSensorState(): SensorData {

        var sensorState = SensorData(-1, -1, -1, -1)

        try{
            val getSensorState = FirestoreProvider.getLastData()
            if(getSensorState != null){
                sensorState = getSensorState
            }
        } catch(e: Exception){
            e.printStackTrace()
        }
        return sensorState
    }
}