package com.example.wherebeam.di

import com.example.wherebeam.data.SensorStateRepositoryImpl
import com.example.wherebeam.domain.GetSensorState
import com.example.wherebeam.domain.SensorStateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorStateModule {
    @Provides
    @Singleton
    fun provideSensorStateUseCase(repository: SensorStateRepository): GetSensorState{
        return GetSensorState(repository)
    }

    @Provides
    @Singleton
    fun provideSensorStateRepository(
    ): SensorStateRepository {
        return SensorStateRepositoryImpl()
    }
}