package com.example.wherebeam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wherebeam.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getValueFromFirebase()
    }
    private fun getValueFromFirebase() = CoroutineScope(Dispatchers.IO).launch{

        val databaseCollection = db.collection("House")

        try{
            val querySnapshot = databaseCollection.get().await()
            querySnapshot.documents[0]
            val document = querySnapshot.documents[0].toObject<TempHumid>()
            withContext(Dispatchers.Main){
                document?.let{
                    binding.humidityText.text = document.humidity.toString()
                    binding.temTextView.text = document.temperature.toString()
                    binding.co2Text.text = document.co2.toString()
                    binding.ecText.text = document.ec.toString()
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}