package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.internal.*

@Serializable
data class FootballLeagueModel (
    val leagueData01: List<LeagueData01>,
    val leagueData02: List<LeagueData02>,
    val leagueData03: JsonArray,
    val leagueData04: List<LeagueData04>,
    val leagueStanding: List<LeagueStanding>,
    val leagueTopscorer: List<League>,
    val leaguePlayercount: League
)

@Serializable
data class LeagueData01 (
    val leagueId: Long,

    val color: String,
    val nameEn: String,
    val nameEnShort: String,

    val nameChs: String,

    val nameChsShort: String,

    val nameCht: String,
    val nameChtShort: String,
    val type: String,
    val subSclassEn: String,

    val subSclassCn: String,

    val sumRound: String,
    val currRound: String,
    val currSeason: String,

    val countryId: String,

    val countryEn: String,

    val countryCn: String,

    val leagueLogo: String,
    val countryLogo: String,

    val areaId: String,

    val nameId: String,

    val nameIdShort: String,

    val subSclassId: String,

    val countryNameEn: String,

    val countryNameCn: String,

    val countryNameId: String
)

@Serializable
data class LeagueData02 (
    val leagueId: Long,

    val subId: Long,

    val subNameEn: String,

    val subNameChs: String,

    val subNameCht: String,
    val num: String,
    val totalRound: String,
    val currentRound: String,
    val hasScore: Boolean,
    val includeSeason: String,
    val isCurrentSub: Boolean,
    val currentSeason: String,
    val isTwoRounds: Boolean,

    val subNameId: String
)

@Serializable
data class LeagueData04 (
    val ruleEn: String,

    val ruleId: String
)

@Serializable
data class League (
    val list: JsonArray? = null
)

@Serializable
data class LeagueStanding (
    val leagueInfo: LeagueInfo,
    val subLeagueInfo: List<SubLeagueInfo>,
    val teamInfo: List<LeagueTeamInfo>,
    val totalStandings: List<TotalStanding>,
    val halfStandings: List<Standing>,
    val homeStandings: List<Standing>,
    val awayStandings: List<Standing>,
    val homeHalfStandings: List<Standing>,
    val awayHalfStandings: List<Standing>,
    val leagueColorInfos: JsonArray,
    val isConference: Boolean,
    val lastUpdateTime: String,
    val scoreCountType: Long
)

@Serializable
data class Standing (
    val rank: Long,

    val teamId: Long,

    val winRate: String,
    val drawRate: String,
    val loseRate: String,
    val winAverage: Double,
    val loseAverage: Double,
    val totalCount: Long,
    val winCount: Long,
    val drawCount: Long,
    val loseCount: Long,
    val getScore: Long,
    val loseScore: Long,
    val goalDifference: Long,
    val integral: Long
)

@Serializable
data class LeagueInfo (
    val leagueId: Long,

    val nameEn: String,

    val nameChs: String,

    val nameCht: String,
    val season: String,
    val color: String,
    val logo: String,
    val nameEnShort: String,

    val nameChsShort: String,

    val nameChtShort: String
)

@Serializable
data class SubLeagueInfo (
    val subId: Long,

    val nameEn: String,

    val nameChs: String,

    val nameCht: String,
    val hasStandings: Boolean,
    val countRound: Long,
    val currRound: Long,
    val isTwoRounds: Boolean,
    val isPrimary: Boolean
)

@Serializable
data class LeagueTeamInfo (
    val flag: String,
    val conferenceFlg: Long,

    @SerialName("teamId")
    val teamID: Long,

    val nameEn: String,

    @SerialName("nameChs")
    val nameCHS: String,

    val nameCht: String
)

@Serializable
data class TotalStanding (
    val rank: Long,

    @SerialName("teamId")
    val teamID: Long,

    val winRate: String,
    val drawRate: String,
    val loseRate: String,
    val winAverage: Double,
    val loseAverage: Double,
    val deduction: Long,

    val deductionExplainCn: String,

    val recentFirstResult: String,
    val recentSecondResult: String,
    val recentThirdResult: String,
    val recentFourthResult: String,
    val recentFifthResult: String,
    val recentSixthResult: String,
    val deductionExplainEn: String,
    val color: Long,
    val redCard: Long,
    val totalCount: Long,
    val winCount: Long,
    val drawCount: Long,
    val loseCount: Long,
    val getScore: Long,
    val loseScore: Long,
    val goalDifference: Long,
    val integral: Long,
    val totalAddScore: Long
)
