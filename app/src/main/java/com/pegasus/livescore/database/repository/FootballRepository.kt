package com.pegasus.livescore.database.repository

import com.pegasus.livescore.database.datasource.FootballDataSource
import com.pegasus.livescore.util.performGetOperation
import com.pegasus.sport.data.dao.FootballDao
import javax.inject.Inject

class FootballRepository @Inject constructor(
    private val remoteDataSource: FootballDataSource,
    private val localDataSource: FootballDao
) {

//    fun getCharacter(id: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getCharacter(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )

    fun getFootballScore() = performGetOperation(
        databaseQuery = { localDataSource.getAllItems() },
        networkCall = { remoteDataSource.getFootballMatch() },
        saveCallResult = { localDataSource.insertAllItems(it.matchList) }
    )
}