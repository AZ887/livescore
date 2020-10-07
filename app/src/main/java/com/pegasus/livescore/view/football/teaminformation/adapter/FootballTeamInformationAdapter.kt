package com.pegasus.livescore.view.football.teaminformation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pegasus.livescore.database.entitymodel.football.FootballTeamInformationModel
import com.pegasus.livescore.view.football.teaminformation.fragment.FootballPlayerFragment
import com.pegasus.livescore.view.football.teaminformation.fragment.FootballTeamFragment
import com.pegasus.livescore.view.football.teaminformation.fragment.FootballTransferRecordFragment


class FootballTeamInformationAdapter(fragment: Fragment, val data: FootballTeamInformationModel) :
    FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FootballTeamFragment(data.teamInfoData)
            1 -> return FootballPlayerFragment(data.teamPlayerData)
            2 -> return FootballTransferRecordFragment(data.teamPlayerTransferData)
        }
        return FootballTeamFragment(data.teamInfoData)
    }

    override fun getItemCount(): Int {
        return 3
    }
}