package com.example.wherebeam.presentation.ui.sensor_state

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wherebeam.databinding.FragmentSensorStateBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class SensorStateFragment : Fragment() {

    private val binding by lazy{ FragmentSensorStateBinding.inflate(layoutInflater)}
    private val sensorStateViewModel : SensorStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        initUI()
        getSensorValue()

        return binding.root
    }
    private fun getSensorValue(){
        sensorStateViewModel.state.observe(viewLifecycleOwner){
            binding.humidityText.text = it.humidity.toString() + "%"
            binding.tempTextView.text = it.temperature.toString() + "°C"
            binding.ecText.text = it.ec.toString() + "dS/m"
            binding.co2Text.text = it.co2.toString() + "ppm"
        }
    }
    private fun initUI(){
        val time = SimpleDateFormat("yyyy.MM.dd (E) a HH:mm").format(System.currentTimeMillis())
        binding.currentTimeTextVIew.text = time
    }
}