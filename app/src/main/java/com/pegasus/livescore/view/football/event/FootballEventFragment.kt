package com.pegasus.livescore.view.football.event

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FootballEventFragmentBinding
import com.pegasus.livescore.databinding.FragmentFootballLiveBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import com.pegasus.livescore.view.football.analysis.FootballAnalysisViewModel
import com.pegasus.livescore.view.football.live.FootballLiveViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballEventFragment : Fragment() {
    private var binding: FootballEventFragmentBinding by autoCleared()
    companion object {
        fun newInstance() = FootballEventFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballEventFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }
}