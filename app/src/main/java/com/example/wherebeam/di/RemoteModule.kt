package com.example.wherebeam.di

import com.example.wherebeam.data.LedStateRepositoryImpl
import com.example.wherebeam.data.SensorStateRepositoryImpl
import com.example.wherebeam.domain.LedStateRepository
import com.example.wherebeam.domain.SensorStateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideSensorStateRepository(
    ): SensorStateRepository {
        return SensorStateRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLedStateRepository(
    ): LedStateRepository{
        return LedStateRepositoryImpl()
    }
}