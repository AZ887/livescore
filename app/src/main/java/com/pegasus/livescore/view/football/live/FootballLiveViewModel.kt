package com.pegasus.livescore.view.football.live

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pegasus.livescore.database.repository.FootballRepository

class FootballLiveViewModel @ViewModelInject constructor(
    repository: FootballRepository
) : ViewModel() {
    val footballLiveList = repository.getFootballScore()
}