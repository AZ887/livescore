package com.pegasus.livescore.database.entitymodel.basketball

import androidx.room.Entity
import androidx.room.PrimaryKey


data class BasketballScoreModel (
    val matchList: List<BasketballMatch>
)

@Entity(tableName = "basketballmatch")
data class BasketballMatch (
    @PrimaryKey
    val matchID: Long,

    val leagueID: Long,

    val leagueEn: String,

    val leagueCHS: String,

    val leagueCht: String,
    val leagueType: Long,
    val color: String,
    val matchTime: String,
    val matchState: Long,
    val remainTime: String,

    val homeTeamID: Long,

    val homeTeamEn: String,

    val homeTeamCHS: String,

    val homeTeamCht: String,
    val homeRankEn: String,

    val homeRankCN: String,
    val awayTeamID: Long,

    val awayTeamEn: String,

    val awayTeamCHS: String,

    val awayTeamCht: String,
    val awayRankEn: String,

    val awayRankCN: String,

    val overtimeCount: Long,
    val homeScore: String,
    val home1: String,
    val home2: String,
    val home3: String,
    val home4: String,
    val homeOT1: String,
    val homeOT2: String,
    val homeOT3: String,
    val awayScore: String,
    val away1: String,
    val away2: String,
    val away3: String,
    val away4: String,
    val awayOT1: String,
    val awayOT2: String,
    val awayOT3: String,
    val hasStats: Boolean,
    val explainEn: String,

    val explainCN: String,

    val tv: String,
    val isNeutral: Boolean,
    val season: String,
    val matchKind: String,

    val cupQualifyID: Long? = null,

    val roundTypeEn: String,

    val roundTypeCHS: String,

    val group: String,
    val homeLogo: String,
    val awayLogo: String,
    val havLiveText: Boolean,
    val havLiveTextEn: Boolean,
    val havLineup: Boolean,
    val havBriefing: Boolean,
    val havAnimate: Boolean,

    val playoffsID: Long? = null
)