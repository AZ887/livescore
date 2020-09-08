package com.pegasus.livescore.database.repository

import com.pegasus.livescore.database.dao.BasketballDao
import com.pegasus.livescore.database.datasource.basketball.BasketballDataSource
import com.pegasus.livescore.util.performGetOperation
import javax.inject.Inject

class BasketballRepository @Inject constructor(
    private val remoteDataSource: BasketballDataSource,
    private val localDataSource: BasketballDao
) {

    fun getBasketballScore() = performGetOperation(
        databaseQuery = { localDataSource.getAllItemsByDate() },
        networkCall = { remoteDataSource.getBasketballMatch() },
        saveCallResult = { localDataSource.insertAllItems(it.matchList) }
    )
}