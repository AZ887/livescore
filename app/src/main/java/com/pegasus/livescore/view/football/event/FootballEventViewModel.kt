package com.pegasus.livescore.view.football.event

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballAnalysisModel
import com.pegasus.livescore.database.entitymodel.football.FootballEventModel
import com.pegasus.livescore.database.repository.FootballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class FootballEventViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    var footballEventList = MutableLiveData<Resource<FootballEventModel>>()
//    private val matchId = savedStateHandle.get<String>("matchId")
//    val homeName = savedStateHandle.get<String>("homeName")
//    val awayName = savedStateHandle.get<String>("awayName")
//    init {
//        viewModelScope.launch {
//            footballEventList.value = repository.
//        }
//    }
}