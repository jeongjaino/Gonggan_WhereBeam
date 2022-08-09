package com.example.wherebeam.domain

import com.example.wherebeam.data.SensorData
import com.example.wherebeam.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSensorState @Inject constructor(
    private val repository: SensorStateRepository
) {
    operator fun invoke(): Flow<Resource<SensorData>> = flow{
        try{
            val sensorState = repository.getSensorState()
            emit(Resource.Success(sensorState))
        }catch (e : IOException){
            e.printStackTrace()
        }
    }
}