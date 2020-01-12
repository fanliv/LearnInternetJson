package com.cyborg.json.network

import androidx.lifecycle.LiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl("https://jsonplaceholder.typicode.com/").build()
interface ToysApiService{
    @GET("photos")
    fun getToys(): Deferred<List<NetworkToy>>
}

object ToysApi{
    val retrofitService: ToysApiService by lazy { retrofit.create(ToysApiService::class.java) }
}