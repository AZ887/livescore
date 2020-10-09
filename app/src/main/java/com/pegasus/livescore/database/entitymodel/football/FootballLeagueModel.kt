package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.*

@Serializable
data class FootballLeagueModel (
    val leagueData01: List<LeagueData01>,
    val leagueData02: List<LeagueData02>,
    val leagueData03: List<LeagueData03>,
    val leagueData04: List<LeagueData04>,
    val leagueStanding: List<LeagueStanding>,
    val leagueTopscorer: List<LeagueTopscorer>,
    val leaguePlayercount: LeaguePlayercount
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
    val sumRound: String? = "0",
    val currRound: String? = "0",
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
data class LeagueData03 (
    val leagueId: Long,

    val season: String,

    val groupId: Long,

    val roundTypeEn: String,
    val roundTypeChs: String,

    val roundTypeCht: String,
    val isGroup: Boolean,
    val groupNum: Any? = null,
    val isCurrGroup: Boolean,
    val numberSort: Long,
    val lineCount: Any? = null,
    val isTwoRounds: Boolean,
    val roundTypeId: String
)

@Serializable
data class LeagueData04 (
    val leagueId: Long,

    val rule: String,

    val ruleCn: String,

    val ruleEn: String,

    val ruleId: String
)

@Serializable
data class LeaguePlayercount (
    val list: Map<String, ListValue>
)

@Serializable
data class ListValue (
    val playerId: Long,

    val teamId: Long,

    val isHome: Boolean,

    val leagueId: Long,

    val season: String,
    val appearanceNum: Long,
    val substituteNum: Long,
    val playingTime: Long,
    val goals: Long,
    val penaltyGoals: Long,
    val shots: Long,
    val shotsTarget: Long,
    val wasFouled: Long,
    val offsides: Long,
    val bestSum: Long,
    val rating: Double,
    val totalPass: Long,
    val passSuc: Long,
    val keyPass: Long,
    val assist: Long,
    val longBall: Long,
    val longBallsSuc: Long,
    val throughBall: Long,
    val throughBallSuc: Long,
    val dribblesSuc: Long,
    val crossNum: Long,
    val crossSuc: Long,
    val tackles: Long,
    val interception: Long,
    val clearance: Long,
    val dispossessed: Long,
    val shotsBlocked: Long,
    val aerialSuc: Long,
    val fouls: Long,
    val red: Long,
    val yellow: Long,
    val turnover: Long,
    val modifyTime: String,
    val teamName: String,

    val teamNameChs: String,

    val teamNameEn: String,
    val playerName: String,

    val playerNameChs: String,

    val playerNameEn: String
)

@Serializable
data class LeagueStanding (
    val leagueInfo: LeagueInfo,
    val teamInfo: List<LeagueTeamInfo>,
    val totalStandings: List<TotalStanding>,
    val halfStandings: List<Standing>,
    val homeStandings: List<Standing>,
    val awayStandings: List<Standing>,
    val homeHalfStandings: List<Standing>,
    val awayHalfStandings: List<Standing>,
    val leagueColorInfos: List<LeagueColorInfo>,
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
data class LeagueColorInfo (
    val color: String,
    val leagueNameEn: String,

    val leagueNameChs: String,

    val leagueNameCht: String
)

@Serializable
data class LeagueInfo (
    val countRound: Long,
    val currRound: Long,

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
data class LeagueTeamInfo (
    val flag: String,
    val conferenceFlg: Long,

    val teamId: Long,

    val nameEn: String,

    val nameChs: String,

    val nameCht: String
)

@Serializable
data class TotalStanding (
    val rank: Long,

    val teamId: Long,

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

@Serializable
data class LeagueTopscorer (
    val list: List<TopScorerList>
)

@Serializable
data class TopScorerList (
    val playerId: Long,

    val playerNameEn: String,

    val playerNameChs: String,

    val playerNameCht: String,
    val countryEn: String,

    val countryCn: String,

    val teamID: Long,
    val teamNameEn: String,

    val teamNameChs: String,

    val teamNameCht: String,
    val goals: Long,
    val homeGoals: Long,
    val awayGoals: Long,
    val homePenalty: Long,
    val awayPenalty: Long
)
