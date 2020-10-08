package com.pegasus.livescore.view.football.teaminformation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.TeamInfo
import com.pegasus.livescore.databinding.CommonVhItemImageDetailBinding
import com.pegasus.livescore.databinding.FootballTeamFragmentBinding
import com.pegasus.livescore.util.autoCleared

class FootballTeamFragment(private val teamInfoData: List<TeamInfo>) : Fragment() {
    private var binding: FootballTeamFragmentBinding by autoCleared()
//    private lateinit var viewModel: FootballTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballTeamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(FootballTeamViewModel::class.java)
        // TODO: Use the ViewModel
        setupUI()
    }

    private fun setupUI(){
        for(teamInfo in teamInfoData) {
            val imageDetailView = CommonVhItemImageDetailBinding.inflate(LayoutInflater.from(context))
            Glide.with(imageDetailView.root)
                .load(teamInfo.logo)
                .placeholder(R.drawable.ic_empty_profile)
                .into(imageDetailView.ivCommonVhItem)
            for(i in resources.getStringArray(R.array.header_team_information_detail).indices){

                val title = resources.getStringArray(R.array.header_team_information_detail)[i]

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
                tvContent.text = " " + mapping(teamInfo, i)
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

    private fun mapping(teamInfo:TeamInfo, index : Int) : String{
        when(index){
            0 -> return teamInfo.nameEn
            1 -> return teamInfo.foundingDate
            2 -> return teamInfo.areaEn
            3 -> return teamInfo.gymEn
            4 -> return teamInfo.capacity
            5 -> return teamInfo.addrEn
            6 -> return teamInfo.website
            7 -> return teamInfo.coachEn
        }
        return ""
    }

}