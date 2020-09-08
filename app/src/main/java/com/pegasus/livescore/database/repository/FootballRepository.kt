package com.pegasus.livescore.database.repository

import com.pegasus.livescore.database.datasource.football.FootballDataSource
import com.pegasus.livescore.util.performGetOperation
import com.pegasus.sport.data.dao.FootballDao
import javax.inject.Inject

class FootballRepository @Inject constructor(
    private val remoteDataSource: FootballDataSource,
    private val localDataSource: FootballDao
) {

    fun getFootballScore() = performGetOperation(
        databaseQuery = { localDataSource.getAllItemsByDate() },
        networkCall = { remoteDataSource.getFootballMatch() },
        saveCallResult = { localDataSource.insertAllItems(it.matchList) }
    )
}