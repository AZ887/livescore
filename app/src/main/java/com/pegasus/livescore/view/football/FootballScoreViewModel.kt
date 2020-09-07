package com.pegasus.livescore.view.football

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pegasus.livescore.database.repository.FootballRepository

class CharactersViewModel @ViewModelInject constructor(
    private val repository: FootballRepository
) : ViewModel() {

    val characters = repository.getFootballScore()
}
