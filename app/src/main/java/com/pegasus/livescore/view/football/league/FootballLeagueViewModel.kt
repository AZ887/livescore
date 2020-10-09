package com.pegasus.livescore.view.football.league

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballLeagueModel
import com.pegasus.livescore.database.entitymodel.football.FootballTeamInformationModel
import com.pegasus.livescore.database.repository.FootballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class FootballLeagueViewModel @ViewModelInject constructor(
    repository: FootballRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var footballLeagueList = MutableLiveData<Resource<FootballLeagueModel>>()

    private val leagueId = savedStateHandle.get<String>("leagueId")
    private val subLeagueId = savedStateHandle.get<String>("subLeagueId")
    private val groupId = savedStateHandle.get<String>("groupId")
    init {
        viewModelScope.launch {
            footballLeagueList.value = repository.getFootballLeague(leagueId.toString(), subLeagueId.toString(), groupId.toString())
        }
    }
}