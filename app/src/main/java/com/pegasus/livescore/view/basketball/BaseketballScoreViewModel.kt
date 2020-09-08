package com.pegasus.livescore.view.basketball

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pegasus.livescore.database.repository.BasketballRepository

class BasketballViewModel @ViewModelInject constructor(
    repository: BasketballRepository
) : ViewModel() {

    val basketballScoreList = repository.getBasketballScore()
}
