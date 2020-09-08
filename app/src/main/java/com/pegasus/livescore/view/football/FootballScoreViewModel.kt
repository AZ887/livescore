package com.pegasus.livescore.view.football

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pegasus.livescore.database.repository.FootballRepository

class FootballViewModel @ViewModelInject constructor(
    repository: FootballRepository
) : ViewModel() {

    val footballScoreList = repository.getFootballScore()
}
