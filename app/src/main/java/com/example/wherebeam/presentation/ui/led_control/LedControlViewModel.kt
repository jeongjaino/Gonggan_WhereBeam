package com.example.wherebeam.presentation.ui.led_control

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wherebeam.data.LedState
import com.example.wherebeam.domain.LedStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LedControlViewModel @Inject constructor(
    private val repository : LedStateRepository
) : ViewModel() {
    private val _state = MutableLiveData<LedState>()
    val state: LiveData<LedState> = _state

    init{
        getSensorData()
    }

    fun onEvent(event : LedControlEvent){
        when(event){
            is LedControlEvent.OnTrigger ->{
                 viewModelScope.launch {
                     repository.setLedState(event.state)
                 }
            }
        }
    }

    private fun getSensorData(){
        viewModelScope.launch {
            repository.getLedState().collectLatest {
                it?.let{
                    _state.postValue(it)
                }
            }
        }
    }
}