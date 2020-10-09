package com.pegasus.livescore.view.football.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.database.entitymodel.football.LeagueData01
import com.pegasus.livescore.databinding.CommonVhItemImageDetailBinding
import com.pegasus.livescore.databinding.FootballTeamFragmentBinding
import com.pegasus.livescore.databinding.FragmentLeagueInformationBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import com.pegasus.livescore.view.football.live.FootballLiveViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class LeagueInformationFragment(private val leagueInfoData: List<LeagueData01>) : Fragment() {
    private var binding: FragmentLeagueInformationBinding by autoCleared()
    private val viewModel: FootballLeagueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeagueInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(FootballTeamViewModel::class.java)
        // TODO: Use the ViewModel
//        setupObserver()
        setupUI()
    }

    private fun setupUI(){
        for(leagueInfo in leagueInfoData) {
            val imageDetailView = CommonVhItemImageDetailBinding.inflate(LayoutInflater.from(context))
            Glide.with(imageDetailView.root)
                .load(leagueInfo.leagueLogo)
                .placeholder(R.drawable.ic_empty_profile)
                .into(imageDetailView.ivCommonVhItem)
            for(i in resources.getStringArray(R.array.header_league_information_detail).indices){

                val title = resources.getStringArray(R.array.header_league_information_detail)[i]

                var linearLayout = LinearLayout(context)

                var tvtitle = TextView(context)
                tvtitle.text = title +  ":"
                var linearLayoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT
                );
                linearLayoutParams.weight = 2F
                tvtitle.layoutParams = linearLayoutParams
                tvtitle.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                tvtitle.textSize = 14F
                linearLayout.addView(tvtitle)

                var tvContent = TextView(context)
                tvContent.text = " " + mapping(leagueInfo, i)
                var linearLayoutParamsTvContent = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT
                );
                linearLayoutParamsTvContent.weight = 3F
                tvContent.layoutParams = linearLayoutParamsTvContent
                tvContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                tvtitle.textSize = 14F
                linearLayout.addView(tvContent)

                imageDetailView.lyCommonVhItem.addView(linearLayout)
            }
            binding.lyFootballTeam.addView(imageDetailView.root)
        }
    }

    private fun mapping(leagueInfo: LeagueData01, index : Int) : String{
        when(index){
            0 -> return leagueInfo.nameEn
            1 -> return leagueInfo.nameEnShort
            2 -> return leagueInfo.type
            3 -> return leagueInfo.subSclassId
            4 -> return leagueInfo.sumRound ?: ""
            5 -> return leagueInfo.currRound ?: ""
            6 -> return leagueInfo.currSeason
            7 -> return leagueInfo.countryEn
            8 -> return leagueInfo.areaId
        }
        return ""
    }

    private fun setupObserver(){
        viewModel.footballLeagueList.observe(viewLifecycleOwner,  {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
//                    if (!it.data?.matchList.isNullOrEmpty()) {
//
//                        adapter.setItems(it.data?.matchList as ArrayList<FootballMatch>)
//                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

//                Resource.Status.LOADING ->
                //todo
//                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

}