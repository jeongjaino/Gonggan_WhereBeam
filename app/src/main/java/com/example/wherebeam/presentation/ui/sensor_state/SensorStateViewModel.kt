package com.example.wherebeam.presentation.ui.sensor_state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wherebeam.data.SensorData
import com.example.wherebeam.domain.SensorStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SensorStateViewModel @Inject constructor(
    private val repository: SensorStateRepository
) : ViewModel() {

    private val _state = MutableLiveData<SensorData>()
    val state: LiveData<SensorData> = _state

    init{
        getSensorData()
    }

    private fun getSensorData(){
        viewModelScope.launch {
            repository.getSensorState().collectLatest {
                it?.let{
                    _state.postValue(it)
                }
            }
        }
    }

}