package com.pegasus.livescore.database.entitymodel.football

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class FootballEventModel (
    val eventList: List<EventList>,
    val technic: List<Technic>
)

@Entity(tableName = "eventlist")
data class EventList (
    @PrimaryKey
    val matchID: Long,

    @Embedded val event: List<Event>
)

data class Event (
    val id: Long,
    val isHome: Boolean,
    val kind: Long,
    val time: String,
    val nameEn: String,

    val nameCHS: String,

    val nameCht: String,

    val playerID: String,

    val playerId2: String,
    val overtime: String
)

@Entity(tableName = "technic")
data class Technic (
    @PrimaryKey
    val matchID: Long,

    val technicCount: String
)
