package com.pegasus.livescore.view.football.live

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pegasus.livescore.database.repository.FootballRepository
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballScoreModel
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class FootballLiveViewModel @ViewModelInject constructor(
    repository: FootballRepository
) : ViewModel() {
    var footballLiveList = MutableLiveData<Resource<FootballScoreModel>>()
init {
    viewModelScope.launch {
        footballLiveList.value = repository.getFootballScore()
    }
}

}