package com.pegasus.livescore.database.service

import com.pegasus.livescore.database.entitymodel.football.FootballAnalysisModel
import com.pegasus.livescore.database.entitymodel.football.FootballEventModel
import com.pegasus.livescore.database.entitymodel.football.FootballScoreModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballService {
    @GET("zqbf-list/")
    suspend fun getFootballScore(
    ): Response<FootballScoreModel>

    @GET("zqbf-list-event/")
    suspend fun getFootballEvent(
    ): Response<FootballEventModel>

    @GET("zqbf-list-lineup/")
    suspend fun getFootballLineup(
    ): Response<FootballEventModel>

    @GET("zqbf-match-analysis/{matchid}")
    suspend fun getFootballAnalysis(@Path("matchid") matchId:String
    ): Response<FootballAnalysisModel>
}