package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var viewModel:MainActivityViewModel
    var data = MutableLiveData<ArrayList<VaccineBody>>()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val dataObserver: Observer<ArrayList<VaccineBody>> =
            Observer { livedata ->
                data.value = livedata
                mainAdapter = MainAdapter(data)
                binding.vaccineRcv.adapter = mainAdapter
            }

        viewModel.liveData.observe(this, dataObserver)
    }

}