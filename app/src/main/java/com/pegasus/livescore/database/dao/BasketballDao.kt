package com.pegasus.livescore.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch

@Dao
interface BasketballDao{
    @Query("SELECT * FROM basketballmatch")
    fun getAllItems(): LiveData<List<BasketballMatch>>

    @Query("SELECT * FROM basketballmatch Order by matchTime")
    fun getAllItemsByDate(): LiveData<List<BasketballMatch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items:List<BasketballMatch>)

    @Query("DELETE FROM basketballmatch")
    suspend fun clearItems()
}