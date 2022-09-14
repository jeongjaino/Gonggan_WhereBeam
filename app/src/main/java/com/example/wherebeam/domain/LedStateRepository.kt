package com.example.wherebeam.domain

import com.example.wherebeam.data.LedState
import kotlinx.coroutines.flow.Flow

interface LedStateRepository {
    suspend fun getLedState() : Flow<LedState?>
    suspend fun setLedState(state: Boolean) : Boolean
}