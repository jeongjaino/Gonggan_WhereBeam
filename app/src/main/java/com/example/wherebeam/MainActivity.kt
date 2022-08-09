package com.example.wherebeam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.wherebeam.databinding.ActivityMainBinding
import com.example.wherebeam.data.SensorData
import com.example.wherebeam.presentation.SensorStateViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    private val sensorStateViewModel : SensorStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getValueFromFirebase()
    }
    private fun getValueFromFirebase(){

        sensorStateViewModel.state.observe(this){
            binding.humidityText.text = it.sensorData.humidity.toString()
            binding.temTextView.text = it.sensorData.temperature.toString()
            binding.ecText.text = it.sensorData.ec.toString()
            binding.co2Text.text = it.sensorData.co2.toString()
        }
    }
}