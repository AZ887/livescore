package com.pegasus.livescore.view.football.league

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pegasus.livescore.R

class FootballLeagueFragment : Fragment() {

    companion object {
        fun newInstance() = FootballLeagueFragment()
    }

    private lateinit var viewModel: FootballLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.football_league_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FootballLeagueViewModel::class.java)
        // TODO: Use the ViewModel
    }

}