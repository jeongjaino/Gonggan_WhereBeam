package com.example.wherebeam.data

data class SensorStateDto(
    val temperature: Long = -1,
    val humidity: Long = -1,
    val co2: Long = -1,
    val ec: Long = -1
){
    fun toSensorState(): SensorData{
        return SensorData(
            temperature = temperature,
            humidity = humidity,
            co2 = co2,
            ec = ec
        )
    }
}