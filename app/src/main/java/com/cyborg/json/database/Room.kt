package com.cyborg.json.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.Deferred

@Dao
interface ToyDao{
    @Query("select * from `toys table`")
    fun getToys(): LiveData<List<DatabaseToy>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(toys: List<DatabaseToy>)
}

@Database(entities = [DatabaseToy::class], version = 1)
abstract class ToysDatabase: RoomDatabase(){
    abstract val toysDao: ToyDao
}

private lateinit var INSTANCE: ToysDatabase
fun getDatabase(context: Context): ToysDatabase {
    synchronized(ToysDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext, ToysDatabase::class.java, "toys").build()
        }
    }
    return INSTANCE
}