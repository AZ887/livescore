package com.pegasus.livescore.view.football.league

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pegasus.livescore.R
import com.pegasus.livescore.databinding.FootballLeagueFragmentBinding
import com.pegasus.livescore.databinding.FragmentFootballLiveBinding
import com.pegasus.livescore.util.autoCleared
import com.pegasus.livescore.view.football.live.FootballLiveViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballLeagueFragment : Fragment() {
    private var binding: FootballLeagueFragmentBinding by autoCleared()
    private val viewModel: FootballLeagueViewModel by viewModels()

    companion object {
        fun newInstance() = FootballLeagueFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballLeagueFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}