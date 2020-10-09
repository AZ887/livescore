package com.pegasus.livescore.view.football.league.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pegasus.livescore.database.entitymodel.football.FootballLeagueModel
import com.pegasus.livescore.database.entitymodel.football.FootballTeamInformationModel
import com.pegasus.livescore.view.football.league.LeagueInformationFragment
import com.pegasus.livescore.view.football.league.LeaguePlayerTechnicalStatisticsFragment
import com.pegasus.livescore.view.football.league.LeagueShooterListFragment
import com.pegasus.livescore.view.football.league.LeagueStandingsFragment
import com.pegasus.livescore.view.football.teaminformation.fragment.FootballPlayerFragment
import com.pegasus.livescore.view.football.teaminformation.fragment.FootballTeamFragment
import com.pegasus.livescore.view.football.teaminformation.fragment.FootballTransferRecordFragment

class FootballLeagueAdapter(fragment: Fragment, val data: FootballLeagueModel) :
    FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
//            0 -> return LeagueInformationFragment(data.teamInfoData)
//            1 -> return LeagueStandingsFragment(data.teamPlayerData)
//            2 -> return LeagueShooterListFragment(data.teamPlayerTransferData)
//            3 -> return LeaguePlayerTechnicalStatisticsFragment(data.teamPlayerTransferData)
        }
        return LeagueInformationFragment(data.leagueData01)
    }

    override fun getItemCount(): Int {
        return 4
    }
}