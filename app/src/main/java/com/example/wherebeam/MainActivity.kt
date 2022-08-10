package com.example.wherebeam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
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
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    private val sensorStateViewModel : SensorStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getSensorValue()

        initUI()
    }
    private fun getSensorValue(){

        sensorStateViewModel.state.observe(this){
            binding.humidityText.text = it.sensorData.humidity.toString() + "%"
            binding.tempTextView.text = it.sensorData.temperature.toString() + "°C"
            binding.ecText.text = it.sensorData.ec.toString() + "dS/m"
            binding.co2Text.text = it.sensorData.co2.toString() + "ppm"
        }
    }
    private fun initUI(){
        val currentTime = SystemClock.elapsedRealtime()
        val sdf = SimpleDateFormat("yyyy.MM.dd (E) a HH:mm")
        binding.currentTimeTextVIew.text = sdf.format(currentTime)
    }

}