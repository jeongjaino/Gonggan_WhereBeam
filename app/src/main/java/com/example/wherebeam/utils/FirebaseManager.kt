package com.example.wherebeam.utils
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseManager {
    val fireStore by lazy { FirebaseFirestore.getInstance() }
    val database by lazy { FirebaseDatabase.getInstance() }
}