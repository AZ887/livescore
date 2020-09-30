package com.pegasus.livescore.database.repository

import com.pegasus.livescore.database.dao.BasketballDao
import com.pegasus.livescore.database.datasource.basketball.BasketballDataSource
import com.pegasus.livescore.util.performGetOperation
import javax.inject.Inject

class BasketballRepository @Inject constructor(
    private val remoteDataSource: BasketballDataSource,
) {

    suspend fun getBasketballScore() = performGetOperation(
        networkCall = { remoteDataSource.getBasketballMatch() },
    )
}