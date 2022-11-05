package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    var liveData: MutableLiveData<ArrayList<VaccineBody>> = MutableLiveData<ArrayList<VaccineBody>>()

    init {
        var vaccineData = ArrayList<VaccineBody>()

        RetrofitObject.getApiService().getInfo(
            1,
            30,
            "OYX/jaNmxOWx6sJGLJpqCDCSGZsdW26LT3J+7mEovNSjl35mPABx7jc0QTHsWVjwXkUfnXmpYD/EXy2+wVx/wQ=="
        ).enqueue(object : Callback<Vaccine> {
            override fun onResponse(call: Call<Vaccine>, response: Response<Vaccine>) {
                vaccineData.addAll(response.body()!!.data)
                liveData.postValue(vaccineData)
            }

            override fun onFailure(call: Call<Vaccine>, t: Throwable) {
                Log.e("Retrofit onFailure", "${t.printStackTrace()}")
            }
        })
    }
}