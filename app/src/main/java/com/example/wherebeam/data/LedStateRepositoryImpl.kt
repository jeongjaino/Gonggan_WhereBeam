package com.example.wherebeam.data

import com.example.wherebeam.domain.LedStateRepository
import com.example.wherebeam.utils.FirebaseManager.fireStore
import com.example.wherebeam.utils.FirestoreCollection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class LedStateRepositoryImpl : LedStateRepository {

    override suspend fun getLedState(): Flow<LedState?> =
        callbackFlow {
            fireStore.collection(FirestoreCollection.smartFarmKey).document("LedControl")
                .addSnapshotListener { snapShot, e ->
                    if (snapShot != null && snapShot.exists()) {
                        val ledStateData: LedState? = snapShot.toObject(LedState::class.java)
                        ledStateData?.let {
                            launch {
                                trySend(it)
                            }
                        } ?: run {
                            launch {
                                trySend(null)
                            }
                        }
                    } else {
                        launch {
                            trySend(null)
                        }
                    }
                }
            awaitClose()
        }

    override suspend fun setLedState(state: Boolean) : Boolean =
        suspendCancellableCoroutine { cont ->
            fireStore.collection(FirestoreCollection.smartFarmKey).document("LedControl")
                .set(LedState(state))
                .addOnSuccessListener {
                    cont.resume(true)
                }
                .addOnCanceledListener {
                    cont.resume(false)
                }
                .addOnFailureListener{
                    cont.resume(false)
                }
        }
}