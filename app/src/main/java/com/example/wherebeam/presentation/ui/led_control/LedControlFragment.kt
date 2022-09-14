package com.example.wherebeam.presentation.ui.led_control

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wherebeam.databinding.FragmentLedControlBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LedControlFragment : Fragment() {

    private val binding by lazy{ FragmentLedControlBinding.inflate(layoutInflater)}
    private val viewModel : LedControlViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        initSwitchButton()
        triggerSwitchButton()
        return binding.root
    }

    private fun initSwitchButton(){
        viewModel.state.observe(viewLifecycleOwner){
            binding.ledSwitchButton.isChecked = it.trigger
        }
    }

    private fun triggerSwitchButton(){
        binding.ledSwitchButton.setOnCheckedChangeListener{ button, isTrigger ->
            viewModel.onEvent(LedControlEvent.OnTrigger(state = isTrigger))
        }
    }

}