package com.pegasus.livescore.database.service

import com.pegasus.livescore.database.entitymodel.FootballScoreModel
import retrofit2.Response
import retrofit2.http.GET

interface FootballService {
    @GET("zqbf-list/")
    suspend fun getFootballScore(
    ): Response<FootballScoreModel>

}