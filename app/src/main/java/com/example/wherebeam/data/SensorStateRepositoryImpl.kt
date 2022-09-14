package com.example.wherebeam.data

import com.example.wherebeam.domain.SensorStateRepository
import com.example.wherebeam.utils.FirebaseProvide.fireStore
import com.example.wherebeam.utils.FirestoreCollection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SensorStateRepositoryImpl: SensorStateRepository {

    override suspend fun getSensorState() :Flow<SensorData?> = callbackFlow {
        fireStore.collection(FirestoreCollection.smartFarmKey).document("SensorValue")
            .addSnapshotListener{ snapShot, e ->
                if (snapShot != null && snapShot.exists()) {
                    val sensorStateData : SensorStateDto? = snapShot.toObject(SensorStateDto::class.java)
                    sensorStateData?.let{
                        launch {
                            trySend(sensorStateData.toSensorState())
                        }
                    } ?: run{
                        launch {
                            trySend(null)
                        }
                    }
                }else{
                    launch {
                        trySend(null)
                    }
                }
            }
        awaitClose()
    }
}