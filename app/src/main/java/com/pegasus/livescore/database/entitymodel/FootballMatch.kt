package com.pegasus.livescore.database.entitymodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "footballmatch")
data class FootballMatch (
    @PrimaryKey
    val matchId: Long,

    val color: String?,
    val kind: Long,

    val leagueId: Long,

    val leagueEn: String?,
    val leagueEnShort: String?,

    val leagueChsShort: String?,

    val leagueChtShort: String?,

    val subLeagueId: String?,

    val subLeagueEn: String?,

    val subLeagueChs: String?,

    val subLeagueCht: String?,
    val matchTime: String?,
    val startTime: String?,
    val homeEn: String?,

    val homeChs: String?,

    val homeCht: String?,
    val awayEn: String?,

    val awayChs: String?,

    val awayCht: String?,

    val homeId: Long,

    val awayId: Long,

    val state: Long,
    val homeScore: Long,
    val awayScore: Long,
    val homeHalfScore: Long,
    val awayHalfScore: Long,
    val homeRed: Long,
    val awayRed: Long,
    val homeYellow: Long,
    val awayYellow: Long,
    val homeCorner: Long,
    val awayCorner: Long,
    val homeRankEn: String?,

    val homeRankCn: String?,

    val awayRankEn: String?,

    val awayRankCn: String?,

    val isNeutral: Boolean,
    val hasLineup: String?,
    val season: String?,
    val roundEn: String?,

    val roundCn: String?,

    val grouping: String?,
    val locationEn: String?,

    val locationCn: String?,

    val weatherEn: String?,

    val weatherCn: String?,

    val temp: String?,
    val explainEn: String?,

    val explainCn: String?,

    val extraExplain: String?,
    val isHidden: Boolean,
    val homeLogo: String?,
    val awayLogo: String?,
    val havAnim: Boolean,
    val animateURL: String?,
    val havBriefing: Boolean,
    val havBriefingEn: Boolean,

    val havBriefingChs: Boolean? = null,

    val groupId: Long? = null
)
