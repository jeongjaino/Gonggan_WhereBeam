package com.example.wherebeam.data

import com.example.wherebeam.domain.SensorStateRepository
import com.example.wherebeam.utils.FirebaseProvide.fireStore
import com.example.wherebeam.utils.FirestoreCollection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class SensorStateRepositoryImpl: SensorStateRepository {

    override suspend fun getSensorState() :Flow<SensorData?> = callbackFlow {
        fireStore.collection(FirestoreCollection.smartFarmKey).document("Room_1")
            .addSnapshotListener{ snapShot, e ->
                if (snapShot != null && snapShot.exists()) {
                    val sensorStateData : SensorStateDto? = snapShot.toObject(SensorStateDto::class.java)
                    sensorStateData?.let{
                        trySend(sensorStateData.toSensorState())
                    } ?: run{
                        trySend(null)
                    }
                }else{
                    trySend(null)
                }
            }
        awaitClose()
    }
}