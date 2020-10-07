package com.pegasus.livescore.database.datasource.football

import com.pegasus.livescore.database.datasource.BaseDataSource
import com.pegasus.livescore.database.service.FootballService
import javax.inject.Inject

class FootballDataSource @Inject constructor(
    private val footballService: FootballService
) : BaseDataSource() {
    suspend fun getFootballMatch() = getResult { footballService.getFootballScore() }
    suspend fun getFootballAnalysis(matchId:String) = getResult { footballService.getFootballAnalysis(matchId) }
    suspend fun getFootballTeamInformation(teamId:String) = getResult { footballService.getFootballTeamInformation(teamId) }
}