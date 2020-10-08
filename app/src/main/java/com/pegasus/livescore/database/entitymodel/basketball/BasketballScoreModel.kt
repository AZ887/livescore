package com.pegasus.livescore.database.entitymodel.basketball

import androidx.room.Entity
import androidx.room.PrimaryKey


data class BasketballScoreModel (
    val matchList: List<BasketballMatch>
)

@Entity(tableName = "basketballmatch")
data class BasketballMatch (
    @PrimaryKey
    val matchId: Long,

    val leagueId: Long,

    val leagueEn: String?,

    val leagueChs: String?,

    val leagueCht: String?,
    val leagueType: Long,
    val color: String?,
    val matchTime: String?,
    val matchState: Long,
    val remainTime: String?,

    val homeTeamId: Long,

    val homeTeamEn: String?,

    val homeTeamChs: String?,

    val homeTeamCht: String?,
    val homeRankEn: String?,

    val homeRankCn: String?,
    val awayTeamId: Long,

    val awayTeamEn: String?,

    val awayTeamCHS: String?,

    val awayTeamCht: String?,
    val awayRankEn: String?,

    val awayRankCn: String?,

    val overtimeCount: Long,
    val homeScore: String?,
    val home1: String?,
    val home2: String?,
    val home3: String?,
    val home4: String?,
    val homeOT1: String?,
    val homeOT2: String?,
    val homeOT3: String?,
    val awayScore: String?,
    val away1: String?,
    val away2: String?,
    val away3: String?,
    val away4: String?,
    val awayOT1: String?,
    val awayOT2: String?,
    val awayOT3: String?,
    val hasStats: Boolean,
    val explainEn: String?,

    val explainCn: String?,

    val tv: String?,
    val isNeutral: Boolean,
    val season: String?,
    val matchKind: String?,

    val cupQualifyId: Long? = 0,

    val roundTypeEn: String?,

    val roundTypeChs: String?,

    val group: String?,
    val homeLogo: String?,
    val awayLogo: String?,
    val havLiveText: Boolean,
    val havLiveTextEn: Boolean,
    val havLineup: Boolean,
    val havBriefing: Boolean,
    val havAnimate: Boolean,

    val playoffsId: Long? = 0
)