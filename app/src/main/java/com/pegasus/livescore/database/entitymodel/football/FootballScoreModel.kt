package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.Serializable


@Serializable
data class FootballScoreModel (
    val matchList: List<FootballMatch>,
    val todayHotLeague: List<TodayHotLeague>,
    val todayHotLeagueList: List<FootballMatch>
)

@Serializable
data class FootballMatch (
    val matchId: Long,

    val color: String,
    val kind: Long,

    val leagueId: Long,

    val leagueEn: String,
    val leagueEnShort: String,

    val leagueChsShort: String,

    val leagueChtShort: String,

    val subLeagueId: String,

    val subLeagueEn: String,

    val subLeagueChs: String,

    val subLeagueCht: String,
    val matchTime: String,
    val startTime: String,
    val homeEn: String,

    val homeChs: String,

    val homeCht: String,
    val awayEn: String,

    val awayChs: String,

    val awayCht: String,

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
    val homeRankEn: String,

    val homeRankCn: String,

    val awayRankEn: String,
    val awayRankCn: String,

    val isNeutral: Boolean,
    val hasLineup: String,
    val season: String,
    val roundEn: String,

    val roundCn: String,

    val grouping: String,
    val locationEn: String,

    val locationCn: String,

    val weatherEn: String,

    val weatherCn: String,

    val temp: String,
    val explainEn: String,

    val explainCn: String,

    val extraExplain: String,
    val isHidden: Boolean,
    val homeLogo: String? = null,
    val awayLogo: String? = null,
    val havEvent: Boolean,
    val havTech: Boolean,
    val havAnim: Boolean,
    val animateURL: String,
    val havBriefing: Boolean,

    val havBriefingChs: Boolean,

    val havPlayerDetails: Boolean,
    val havLineup: Boolean,
    val havTextLive: Boolean,
    val odds: Odds,
    val leagueNameEn: String,

    val leagueNameId: String,

    val leagueNameCn: String,

    val leagueIdShort: String,

    val subLeagueNameEn: String,

    val subLeagueNameId: String,

    val subLeagueNameCn: String,

    val homeNameEn: String,

    val homeNameId: String,

    val homeNameCn: String,

    val awayNameEn: String,

    val awayNameId: String,

    val awayNameCn: String,

    val homeRankId: String,

    val roundId: String,

    val locationId: String,

    val weatherId: String,

    val explainId: String,

    val groupId: Long? = null
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
    val leagueId: Long,

    val leagueEn: String,
    val leagueEnShort: String
)