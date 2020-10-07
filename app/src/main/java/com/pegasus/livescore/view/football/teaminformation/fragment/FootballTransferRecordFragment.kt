package com.pegasus.livescore.view.football.teaminformation.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.TeamPlayer
import com.pegasus.livescore.view.football.teaminformation.viewmodel.FootballTransferRecordViewModel

class FootballTransferRecordFragment(val teamPlayerTransferData: List<TeamPlayer>) : Fragment() {

    private lateinit var viewModel: FootballTransferRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.football_transfer_record_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FootballTransferRecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}