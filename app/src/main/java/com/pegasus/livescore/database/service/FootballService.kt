package com.pegasus.livescore.database.service

import com.pegasus.livescore.database.entitymodel.FootballEventModel
import com.pegasus.livescore.database.entitymodel.FootballScoreModel
import retrofit2.Response
import retrofit2.http.GET

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
}