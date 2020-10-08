package com.pegasus.livescore.view.football.live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballEvent
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.database.entitymodel.football.FootballTechnic
import com.pegasus.livescore.databinding.FragmentFootballLiveBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.*
import javax.inject.Inject

@AndroidEntryPoint
class FootballLiveFragment : Fragment(), FootballLiveAdapter.FootballLiveItemListener {

    private var binding: FragmentFootballLiveBinding by autoCleared()
    private val viewModel: FootballLiveViewModel by viewModels()
    private lateinit var adapter: FootballLiveAdapter
    private var eventList:MutableList<FootballEvent> = mutableListOf()
    private var eventTechnic: MutableList<FootballTechnic> = mutableListOf()


    companion object {
        fun newInstance() = FootballLiveFragment()
    }

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFootballLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }


    private fun setupRecyclerView() {
        adapter = FootballLiveAdapter(this)
        binding.rvFragmentFootballLive.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentFootballLive.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.footballLiveList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.matchList.isNullOrEmpty()) {

                        adapter.setItems(it.data?.matchList as ArrayList<FootballMatch>)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

//                Resource.Status.LOADING ->
                //todo
//                    binding.progressBar.visibility = View.VISIBLE
            }
        })
        viewModel.footballEventList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.eventList.isNullOrEmpty()) {

                        eventList.addAll(it.data?.eventList as ArrayList)
                    }
                    if (!it.data?.technic.isNullOrEmpty()) {

                        eventTechnic.addAll(it.data?.technic as ArrayList)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

//                Resource.Status.LOADING ->
                //todo
//                    binding.progressBar.visibility = View.VISIBLE
            }
        })

    }

    override fun onClickViewHolder(action: String, item: FootballMatch) {
        when (action) {
            "homeTeam" -> {
                val action =
                    FootballLiveFragmentDirections.actionNavFootballLiveToNavFootballTeamInformation(
                        item.homeId.toString()
                    )
                findNavController().navigate(action)
            }
            "awayTeam" -> {
                val action =
                    FootballLiveFragmentDirections.actionNavFootballLiveToNavFootballTeamInformation(
                        item.awayId.toString()
                    )
                findNavController().navigate(action)
            }
            resources.getString(R.string.live_button_analysis_text) -> {
                val action =
                    FootballLiveFragmentDirections.actionNavFootballLiveToFootballAnalysisFragment(
                        item.matchId.toString(),
                        item.homeNameEn,
                        item.awayNameEn
                    )
                findNavController().navigate(action)
            }
            resources.getString(R.string.live_button_league_text) -> {
                val action =
                    FootballLiveFragmentDirections.actionNavFootballLiveToNavFootballLeague(
                        item.leagueId.toString(),
                        item.subLeagueId,
                        item.groupId.toString()
                    )
                findNavController().navigate(action)
            }
            resources.getString(R.string.live_button_event_text) -> {

//                val action =
//                    FootballLiveFragmentDirections.actionNavFootballLiveToNavFootballEvent(
//                        item.leagueId.toString(),
//                        item.subLeagueId,
//                        item.groupId.toString()
//                    )
//                findNavController().navigate(action)
            }
        }
    }
}