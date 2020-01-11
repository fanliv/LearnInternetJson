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
import retrofit2.HttpException
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
            val getToysDeferred = ToysApi.retrofitService.getToys()
            try {
                _toys.value = getToysDeferred.await()
                Log.i("luffy", "success")
            }catch (e: HttpException){
                Log.i("luffy", "${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
