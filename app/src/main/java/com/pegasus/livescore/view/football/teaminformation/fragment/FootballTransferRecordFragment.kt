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
import com.pegasus.livescore.database.entitymodel.football.TeamPlayerTransfer
import com.pegasus.livescore.databinding.CommonVhEmptyItemBinding
import com.pegasus.livescore.databinding.CommonVhItemImageDetailBinding
import com.pegasus.livescore.databinding.FootballTransferRecordFragmentBinding
import com.pegasus.livescore.util.autoCleared

class FootballTransferRecordFragment(val teamPlayerTransferData: List<TeamPlayerTransfer>) : Fragment() {
    private var binding : FootballTransferRecordFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballTransferRecordFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        if(teamPlayerTransferData.isEmpty()){
            val emptyView = CommonVhEmptyItemBinding.inflate(LayoutInflater.from(context)).root
            binding.lyFootballTeam.addView(emptyView);
        }else{

            for(teamPlayer in teamPlayerTransferData) {
                val imageDetailView = CommonVhItemImageDetailBinding.inflate(LayoutInflater.from(context))
                Glide.with(imageDetailView.root)
                    .load(teamPlayer.playerPhoto)
                    .placeholder(R.drawable.ic_empty_profile)
                    .into(imageDetailView.ivCommonVhItem)
                for(i in resources.getStringArray(R.array.header_team_player_transfer_detail).indices){

                    val title = resources.getStringArray(R.array.header_team_player_transfer_detail)[i]

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
    }

    private fun mapping(teamInfoTransfer: TeamPlayerTransfer, index : Int) : String{
        when(index){
            0 -> return teamInfoTransfer.playerNameEn
            1 -> return teamInfoTransfer.transferTime
            2 -> return teamInfoTransfer.endTime
            3 -> return teamInfoTransfer.fromTeamEn
            4 -> return teamInfoTransfer.toTeamEn
            5 -> return teamInfoTransfer.money
            6 -> return teamInfoTransfer.season
            7 -> return teamInfoTransfer.typeEn
        }
        return ""
    }
}