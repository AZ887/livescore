package com.pegasus.livescore.view.football.event

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pegasus.livescore.R

class FootballEventFragment : Fragment() {

    companion object {
        fun newInstance() = FootballEventFragment()
    }

    private lateinit var viewModel: FootballEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.football_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FootballEventViewModel::class.java)
        // TODO: Use the ViewModel
    }

}