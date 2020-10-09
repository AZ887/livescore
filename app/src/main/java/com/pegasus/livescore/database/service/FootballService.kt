package com.pegasus.livescore.database.service

import com.pegasus.livescore.database.entitymodel.football.*
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

    @GET("zqbf-list-team/{teamid}")
    suspend fun getFootballTeamInformation(@Path("teamid") teamId:String
    ): Response<FootballTeamInformationModel>

    @GET("zqbf-list-league/{leagueid}/{subleagueid}/{groupid}")
    suspend fun getFootballLeague(
        @Path("leagueid") leagueId:String,
        @Path("subleagueid") subLeagueId:String,
        @Path("groupid") groupId:String,
    ): Response<FootballLeagueModel>
}