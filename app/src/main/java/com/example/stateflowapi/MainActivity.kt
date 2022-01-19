package com.example.stateflowapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.stateflowapi.ViewModel.MainViewModel
import com.example.stateflowapi.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        lifecycleScope.launch {
            mainViewModel.counter.collect { counter->
                binding.result.text = counter.toString()
            }
        }


        binding.increment.setOnClickListener {
            mainViewModel.increment()
        }

        binding.decrement.setOnClickListener {
            mainViewModel.decrement()
        }

    }
}