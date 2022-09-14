package com.example.wherebeam.presentation.ui.led_control


sealed class LedControlEvent {

    data class OnTrigger(val state: Boolean) : LedControlEvent()
}