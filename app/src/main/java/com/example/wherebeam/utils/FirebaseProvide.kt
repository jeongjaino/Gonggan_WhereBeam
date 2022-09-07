package com.example.wherebeam.utils
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseProvide {
    val fireStore by lazy{ FirebaseFirestore.getInstance() }
}