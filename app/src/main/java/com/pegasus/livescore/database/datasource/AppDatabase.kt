package com.pegasus.livescore.database.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pegasus.livescore.database.entitymodel.FootballMatch
import com.pegasus.sport.data.dao.FootballDao

@Database(entities = [FootballMatch::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun footballDao(): FootballDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "livescore")
                .fallbackToDestructiveMigration()
                .build()
    }

}