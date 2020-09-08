package com.pegasus.livescore.database.datasource.basketball

import com.pegasus.livescore.database.datasource.BaseDataSource
import com.pegasus.livescore.database.service.BasketballService
import com.pegasus.livescore.database.service.FootballService
import javax.inject.Inject

class BasketballDataSource @Inject constructor(
    private val basketballService: BasketballService
) : BaseDataSource() {
    suspend fun getBasketballMatch() = getResult { basketballService.getBasketballScore() }
}