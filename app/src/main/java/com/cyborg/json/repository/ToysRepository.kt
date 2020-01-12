package com.cyborg.json.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cyborg.json.database.ToysDatabase
import com.cyborg.json.database.asDomainToy
import com.cyborg.json.domain.DomainToy
import com.cyborg.json.network.ToysApi
import com.cyborg.json.network.asDatabaseToy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToysRepository(val database: ToysDatabase) {
    val toys: LiveData<List<DomainToy>> = Transformations.map(database.toysDao.getToys()) {
        it.asDomainToy()
    }

    suspend fun refreshToys() {
        withContext(Dispatchers.IO) {
            val toysList = ToysApi.retrofitService.getToys().await()
            database.toysDao.insertAll(toysList.asDatabaseToy())
        }
    }
}