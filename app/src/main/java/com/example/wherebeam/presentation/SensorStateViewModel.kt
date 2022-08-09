package com.example.wherebeam.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wherebeam.data.SensorState
import com.example.wherebeam.domain.GetSensorState
import com.example.wherebeam.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SensorStateViewModel @Inject constructor(
    private val getSensorState: GetSensorState
) : ViewModel() {

    private val _state = MutableLiveData<SensorState>()
    val state: LiveData<SensorState> = _state

    init{
        getSensorData()
    }

    private fun getSensorData(){
        getSensorState().onEach { result ->
            when (result){
                is Resource.Success ->{
                    _state.value = SensorState(false, sensorData = result.data!!, "")
                }
            }
        }.launchIn(viewModelScope)
    }

}