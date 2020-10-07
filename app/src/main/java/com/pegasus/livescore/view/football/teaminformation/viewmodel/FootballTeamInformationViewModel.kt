package com.pegasus.livescore.view.football.teaminformation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballTeamInformationModel
import com.pegasus.livescore.database.repository.FootballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class FootballTeamInformationViewModel @ViewModelInject constructor(
    repository: FootballRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var footballTeamInformationList = MutableLiveData<Resource<FootballTeamInformationModel>>()

    private val teamId = savedStateHandle.get<String>("teamId")
    init {
        viewModelScope.launch {
            footballTeamInformationList.value = repository.getFootballTeamInformation(teamId.toString())
        }
    }
}