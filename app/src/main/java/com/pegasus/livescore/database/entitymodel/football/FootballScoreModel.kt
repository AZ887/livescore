package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FootballScoreModel (
    val matchList: List<FootballMatch>,
    val todayHotLeague: List<TodayHotLeague>,
    val todayHotLeagueList: List<FootballMatch>
)

@Serializable
data class FootballMatch (
    @SerialName("matchId")
    val matchID: Long,

    val color: String,
    val kind: Long,

    @SerialName("leagueId")
    val leagueID: Long,

    val leagueEn: String,
    val leagueEnShort: String,

    @SerialName("leagueChsShort")
    val leagueCHSShort: String,

    val leagueChtShort: String,

    @SerialName("subLeagueId")
    val subLeagueID: String,

    val subLeagueEn: String,

    @SerialName("subLeagueChs")
    val subLeagueCHS: String,

    val subLeagueCht: String,
    val matchTime: String,
    val startTime: String,
    val homeEn: String,

    @SerialName("homeChs")
    val homeCHS: String,

    val homeCht: String,
    val awayEn: String,

    @SerialName("awayChs")
    val awayCHS: String,

    val awayCht: String,

    @SerialName("homeId")
    val homeID: Long,

    @SerialName("awayId")
    val awayID: Long,

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
    val homeRankEn: String,

    @SerialName("homeRankCn")
    val homeRankCN: String,

    val awayRankEn: String,

    @SerialName("awayRankCn")
    val awayRankCN: String,

    val isNeutral: Boolean,
    val hasLineup: String,
    val season: String,
    val roundEn: String,

    @SerialName("roundCn")
    val roundCN: String,

    val grouping: String,
    val locationEn: String,

    @SerialName("locationCn")
    val locationCN: String,

    val weatherEn: String,

    @SerialName("weatherCn")
    val weatherCN: String,

    val temp: String,
    val explainEn: String,

    @SerialName("explainCn")
    val explainCN: String,

    val extraExplain: String,
    val isHidden: Boolean,
    val homeLogo: String? = null,
    val awayLogo: String? = null,
    val havEvent: Boolean,
    val havTech: Boolean,
    val havAnim: Boolean,
    val animateURL: String,
    val havBriefing: Boolean,

    @SerialName("havBriefingChs")
    val havBriefingCHS: Boolean,

    val havPlayerDetails: Boolean,
    val havLineup: Boolean,
    val havTextLive: Boolean,
    val odds: Odds,
    val leagueNameEn: String,

    @SerialName("leagueNameId")
    val leagueNameID: String,

    @SerialName("leagueNameCn")
    val leagueNameCN: String,

    @SerialName("leagueIdShort")
    val leagueIDShort: String,

    val subLeagueNameEn: String,

    @SerialName("subLeagueNameId")
    val subLeagueNameID: String,

    @SerialName("subLeagueNameCn")
    val subLeagueNameCN: String,

    val homeNameEn: String,

    @SerialName("homeNameId")
    val homeNameID: String,

    @SerialName("homeNameCn")
    val homeNameCN: String,

    val awayNameEn: String,

    @SerialName("awayNameId")
    val awayNameID: String,

    @SerialName("awayNameCn")
    val awayNameCN: String,

    @SerialName("homeRankId")
    val homeRankID: String,

    @SerialName("roundId")
    val roundID: String,

    @SerialName("locationId")
    val locationID: String,

    @SerialName("weatherId")
    val weatherID: String,

    @SerialName("explainId")
    val explainID: String,

    @SerialName("groupId")
    val groupID: Long? = null
)

@Serializable
data class Odds (
    val handicap: List<String>? = null,
    val handicapHalf: List<String>? = null,
    val europeOdds: List<String>? = null,
    val overUnder: List<String>? = null,
    val overUnderHalf: List<String>? = null
)

@Serializable
data class TodayHotLeague (
    @SerialName("leagueId")
    val leagueID: Long,

    val leagueEn: String,
    val leagueEnShort: String
)