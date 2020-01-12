package com.cyborg.json.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.cyborg.json.database.getDatabase
import com.cyborg.json.network.ToysApi
import com.cyborg.json.repository.ToysRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ToysRepository(getDatabase(application))
    val toys = repository.toys
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getToysFromInternet()
    }

    private fun getToysFromInternet() {
        coroutineScope.launch {
            val getToysDeferred = ToysApi.retrofitService.getToys()
            try {
                repository.refreshToys()
                Log.i("luffy", "success")
            }catch (e: IOException){
                Log.i("luffy", "${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
