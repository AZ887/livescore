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
import com.pegasus.livescore.database.entitymodel.football.TeamPlayer
import com.pegasus.livescore.databinding.CommonVhItemImageDetailBinding
import com.pegasus.livescore.databinding.FootballPlayerFragmentBinding
import com.pegasus.livescore.util.autoCleared

class FootballPlayerFragment(private val teamPlayerData: List<TeamPlayer>) : Fragment() {
    private var binding : FootballPlayerFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballPlayerFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        // TODO: Use the ViewModel
    }

    private fun setupUI() {
        for(teamPlayer in teamPlayerData) {
            val imageDetailView = CommonVhItemImageDetailBinding.inflate(LayoutInflater.from(context))
            Glide.with(imageDetailView.root)
                .load(teamPlayer.photo)
                .placeholder(R.drawable.ic_empty_profile)
                .into(imageDetailView.ivCommonVhItem)
            for(i in resources.getStringArray(R.array.header_team_player_detail).indices){

                val title = resources.getStringArray(R.array.header_team_player_detail)[i]

                var linearLayout = LinearLayout(context)

                var tvtitle = TextView(context)
                tvtitle.text = title +  ":"
                var linearLayoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT
                );
                linearLayoutParams.weight = 2F
                tvtitle.layoutParams = linearLayoutParams
                tvtitle.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                linearLayout.addView(tvtitle)

                var tvContent = TextView(context)
                tvContent.text = " " + mapping(teamPlayer, i)
                var linearLayoutParamsTvContent = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT
                );
                linearLayoutParamsTvContent.weight = 3F
                tvContent.layoutParams = linearLayoutParamsTvContent
                tvContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                linearLayout.addView(tvContent)

                imageDetailView.lyCommonVhItem.addView(linearLayout)
            }
            binding.lyFootballTeam.addView(imageDetailView.root)
        }
    }

    private fun mapping(teamInfo: TeamPlayer, index : Int) : String{
        when(index){
            0 -> return teamInfo.nameEn
            1 -> return teamInfo.birthday
            2 -> return teamInfo.height
            3 -> return teamInfo.weight
            4 -> return teamInfo.countryEn
            5 -> return teamInfo.value
            6 -> return teamInfo.feetEn
            7 -> return teamInfo.positionEn
            7 -> return teamInfo.number
            7 -> return teamInfo.endDateContract
        }
        return ""
    }

}