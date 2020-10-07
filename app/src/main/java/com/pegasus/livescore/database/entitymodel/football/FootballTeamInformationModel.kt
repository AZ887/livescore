package com.pegasus.livescore.database.entitymodel.football

import kotlinx.serialization.Serializable


@Serializable
data class FootballTeamInformationModel (
    val teamInfoData: List<TeamInfo>,
    val teamPlayerData: List<TeamPlayer>,
    val teamPlayerTransferData: List<TeamPlayer>
)

@Serializable
data class TeamInfo (
    val teamId: Long,

    val leagueId: Long,

    val nameEn: String,

    val nameChs: String,

    val nameCht: String,
    val foundingDate: String,
    val areaEn: String,

    val areaCn: String,

    val gymEn: String,

    val gymCn: String,

    val capacity: String,
    val logo: String,
    val addrEn: String,

    val addrCn: String,

    val website: String,
    val coachEn: String,

    val coachCn: String,

    val nameId: String,

    val areaId: String,

    val gymId: String,

    val coachId: String
)

@Serializable
data class TeamPlayer (
    val id: Long,

    val playerId: Long,

    val nameEn: String,

    val nameChs: String,

    val nameCht: String,
    val birthday: String,
    val height: String,
    val weight: String,
    val countryEn: String,

    val countryCn: String,

    val countryId: String,

    val photo: String,
    val value: String,
    val feetEn: String,

    val feetCn: String,

    val introduceEn: String,

    val introduceCn: String,

    val teamID: Long,
    val positionEn: String,

    val positionCn: String,

    val number: String,
    val endDateContract: String,

    val teamId: Long,

    val nameId: String,

    val countryNameEn: String,

    val countryNameCn: String,

    val countryNameId: String,

    val feetId: String,

    val introduceId: String,

    val positionId: String
)