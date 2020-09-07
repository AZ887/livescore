package com.pegasus.livescore.database.entitymodel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class FootballLineUpModel (
    val lineupList: List<LineupList>
)

@Entity(tableName = "lineuplist")
data class LineupList (
    @PrimaryKey
    val matchID: Long,

    val homeArray: String,
    val awayArray: String,
    @Embedded val homeLineup: List<AwayBackup>,
    @Embedded val awayLineup: List<AwayBackup>,
    @Embedded val homeBackup: List<AwayBackup>,
    @Embedded val awayBackup: List<AwayBackup>
)

data class AwayBackup (
    val playerID: Long,

    val nameEn: String,

    val nameCHS: String,

    val nameCht: String,
    val number: String,

    val positionID: String
)