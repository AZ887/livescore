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
    val matchId = savedStateHandle.get<String>("matchId")
    val homeName = savedStateHandle.get<String>("homeName")
    val awayName = savedStateHandle.get<String>("awayName")
    init {
        viewModelScope.launch {
//            var response = repository.getFootballAnalysis(matchId.toString())
//            response.data?.list?.let { convertListToMap(viewHolderModel, it) }
            footballAnalysisList.value = repository.getFootballAnalysis(matchId.toString())
        }
    }

    private fun convertListToMap(map: MutableMap<String, List<String>>, responseList: List<Map<String, List<List<String>>>>){
        for(mainList in responseList){
            if(mainList.isNotEmpty()){
                mainList.forEach { (key, value) ->
                    println("$key = $value")
                    var listjoinString : MutableList<String> = mutableListOf()
                    for(item in value){
                        listjoinString.add(item.joinToString())
                    }
                    map.put(key,listjoinString)
                }
            }
        }
    }
}