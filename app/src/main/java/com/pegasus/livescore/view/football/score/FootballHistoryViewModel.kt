package com.pegasus.livescore.view.football.score

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballScoreModel
import com.pegasus.livescore.database.repository.FootballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class FootballViewModel @ViewModelInject constructor(
    repository: FootballRepository
) : ViewModel() {
    var footballScoreList = MutableLiveData<Resource<FootballScoreModel>>()
init {
    viewModelScope.launch {
        footballScoreList.value = repository.getFootballScore()
    }

}

}