package com.pegasus.livescore.database.repository

import com.pegasus.livescore.database.datasource.football.FootballDataSource
import com.pegasus.livescore.util.performGetOperation
import com.pegasus.livescore.database.dao.FootballDao
import javax.inject.Inject

class FootballRepository @Inject constructor(
    private val remoteDataSource: FootballDataSource,
) {
    suspend fun getFootballScore() = performGetOperation(
        networkCall = { remoteDataSource.getFootballMatch() }
    )
    suspend fun getFootballAnalysis(matchId: String) = performGetOperation (
        networkCall = {remoteDataSource.getFootballAnalysis(matchId)}
    )

    suspend fun getFootballTeamInformation(teamId: String) = performGetOperation (
        networkCall = {remoteDataSource.getFootballTeamInformation(teamId)}
    )

    suspend fun getFootballEvent() = performGetOperation (
        networkCall = {remoteDataSource.getFootballEvent()}
    )

    suspend fun getFootballLeague(leagueId:String,subLeagueId:String,groupId:String) = performGetOperation (
        networkCall = {remoteDataSource.getFootballLeague(leagueId, subLeagueId, groupId)}
    )
}