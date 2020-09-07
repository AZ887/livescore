package com.pegasus.sport.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pegasus.livescore.database.entitymodel.FootballMatch

@Dao
interface FootballDao{
    @Query("SELECT * FROM footballmatch")
    fun getAllItems(): LiveData<List<FootballMatch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items:List<FootballMatch>)

    @Query("DELETE FROM footballmatch")
    suspend fun clearItems()
}