package com.cyborg.json.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyborg.json.network.ToyNetworkModel
import com.cyborg.json.network.ToysApi
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _toys = MutableLiveData<List<ToyNetworkModel>>()
    val toys: LiveData<List<ToyNetworkModel>>
        get() =  _toys
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getToysFromInternet()
    }

    private fun getToysFromInternet() {
        coroutineScope.launch {
            try {
                _toys.value = ToysApi.retrofitService.getToys().await()
                Log.i("luffy", "success")
            }catch (e: Exception){
                Log.i("luffy", "${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
