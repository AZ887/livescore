package com.pegasus.livescore.view.football.analysis

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballAnalysisModel
import com.pegasus.livescore.database.repository.FootballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class FootballAnalysisViewModel @ViewModelInject constructor(
    repository: FootballRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    var footballAnalysisList = MutableLiveData<Resource<FootballAnalysisModel>>()
//    var viewHolderModel : MutableMap<String, List<String>> = mutableMapOf()
    private val matchId = savedStateHandle.get<String>("matchId")
    val homeName = savedStateHandle.get<String>("homeName")
    val awayName = savedStateHandle.get<String>("awayName")
    init {
        viewModelScope.launch {
            footballAnalysisList.value = repository.getFootballAnalysis(matchId.toString())
        }
    }
}