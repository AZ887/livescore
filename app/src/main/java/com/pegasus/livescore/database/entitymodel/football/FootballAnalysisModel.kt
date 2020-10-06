package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.Serializable

@Serializable
data class FootballAnalysisModel (
    val list: List<Map<String, List<List<String>>>>,
    val referee: List<Referee>
)

@Serializable
data class Referee (
    val matchId: Long,

    val typeId: Long,

    val refereeId: Long,

    val nameEn: String,

    val nameChs: String,

    val nameCht: String,
    val birthday: String,
    val countryEn: String,

    val countryChs: String,

    val countryCht: String,
    val photo: String
)


