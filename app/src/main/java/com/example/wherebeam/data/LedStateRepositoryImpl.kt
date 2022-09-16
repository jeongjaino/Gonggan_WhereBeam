package com.example.wherebeam.data
import com.example.wherebeam.domain.LedStateRepository
import com.example.wherebeam.utils.FirebaseManager.database
import com.example.wherebeam.utils.FirestoreCollection.smartFarmKey
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
            database.reference.child(smartFarmKey).child("LedControl/trigger")
                .addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        launch {
                            trySend(LedState(true))
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        launch {
                            trySend(
                                LedState(false)
                            )
                        }
                    }
                    }
                )
            awaitClose()
        }

    override suspend fun setLedState(state: Boolean) : Boolean =
        suspendCancellableCoroutine { cont ->
            database.reference.child(smartFarmKey).child("LedControl/trigger")
                .setValue(LedState(state))
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