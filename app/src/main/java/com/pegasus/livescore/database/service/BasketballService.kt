package com.pegasus.livescore.database.service

import com.pegasus.livescore.database.entitymodel.basketball.BasketballScoreModel
import retrofit2.Response
import retrofit2.http.GET

interface BasketballService {
    @GET("lqbf-list/")
    suspend fun getBasketballScore(
    ): Response<BasketballScoreModel>
}