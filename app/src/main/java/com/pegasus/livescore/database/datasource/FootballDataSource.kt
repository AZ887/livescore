package com.pegasus.livescore.database.datasource

import com.pegasus.livescore.database.service.FootballService
import javax.inject.Inject

class FootballDataSource @Inject constructor(
    private val footballService: FootballService
) : BaseDataSource() {
    suspend fun getFootballMatch() = getResult { footballService.getFootballScore() }
}