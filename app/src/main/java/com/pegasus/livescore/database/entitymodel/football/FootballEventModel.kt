package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.Serializable

@Serializable
data class FootballEventModel (
    val eventList: List<FootballEvent>,
    val technic: List<FootballTechnic>
)

@Serializable
data class FootballEvent (
    val matchId: Long,
    val event: List<Event>
)

@Serializable
data class Event (
    val id: Long,
    val isHome: Boolean,
    val kind: Long,
    val time: String,
    val nameEn: String,

    val nameChs: String,

    val nameCht: String,

    val playerId: String,

    val playerId2: String,
    val overtime: String
)

@Serializable
data class FootballTechnic (
    val matchId: Long,

    val technicCount: String
)
