package com.pegasus.livescore.view.basketball.score

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.basketball.BasketballScoreModel
import com.pegasus.livescore.database.entitymodel.football.FootballScoreModel
import com.pegasus.livescore.database.repository.BasketballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class BasketballHistoryViewModel @ViewModelInject constructor(
    repository: BasketballRepository
) : ViewModel() {
    var basketballScoreList = MutableLiveData<Resource<BasketballScoreModel>>()
    init {
        viewModelScope.launch {
            basketballScoreList.value = repository.getBasketballScore()
        }
    }
}
