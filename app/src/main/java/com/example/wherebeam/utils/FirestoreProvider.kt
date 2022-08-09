package com.example.wherebeam.utils

import com.example.wherebeam.data.SensorData
import com.example.wherebeam.data.SensorStateDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object FirestoreProvider {

    private val sensorRef = FirebaseFirestore.getInstance().collection(FirestoreCollection.smartFarmKey)

    suspend fun getLastData() = suspendCoroutine<SensorData?>{ continuation ->

        val querySnapshot = sensorRef.get()

        querySnapshot.addOnSuccessListener {
            if(!querySnapshot.result.isEmpty){
                val document = querySnapshot.result.documents[0]
                val sensorStateData : SensorStateDto? = document.toObject(SensorStateDto::class.java)
                sensorStateData?.let{
                    continuation.resume(sensorStateData.toSensorState())
                } ?: run{
                    continuation.resume(null)
                }
            }else{
                continuation.resume(null)
            }
        }
            .addOnFailureListener{
                continuation.resumeWithException(it)
            }

    }
}