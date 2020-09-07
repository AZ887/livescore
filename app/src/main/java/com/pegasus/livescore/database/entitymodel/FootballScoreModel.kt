package com.pegasus.livescore.database.entitymodel

data class FootballScoreModel (
    val info: Info,
    val matchList: List<FootballMatch>
)

