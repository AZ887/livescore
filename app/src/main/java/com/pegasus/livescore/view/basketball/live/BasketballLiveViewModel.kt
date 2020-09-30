package com.pegasus.livescore.view.basketball.live

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.basketball.BasketballScoreModel
import com.pegasus.livescore.database.repository.BasketballRepository
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch

class BasketballLiveViewModel @ViewModelInject constructor(
    repository: BasketballRepository
) : ViewModel() {
    var basketballLiveList = MutableLiveData<Resource<BasketballScoreModel>>()
    init {
        viewModelScope.launch {
            basketballLiveList.value = repository.getBasketballScore()
        }
    }
}