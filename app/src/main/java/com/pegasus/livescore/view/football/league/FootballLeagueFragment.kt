package com.pegasus.livescore.view.football.league

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballLeagueModel
import com.pegasus.livescore.database.entitymodel.football.FootballTeamInformationModel
import com.pegasus.livescore.databinding.FootballLeagueFragmentBinding
import com.pegasus.livescore.databinding.FragmentFootballLiveBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import com.pegasus.livescore.view.football.league.adapter.FootballLeagueAdapter
import com.pegasus.livescore.view.football.live.FootballLiveViewModel
import com.pegasus.livescore.view.football.teaminformation.adapter.FootballTeamInformationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballLeagueFragment : Fragment() {
    private var binding: FootballLeagueFragmentBinding by autoCleared()
    private val viewModel: FootballLeagueViewModel by viewModels()

    companion object {
        fun newInstance() = FootballLeagueFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballLeagueFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver()
    }

    private fun setupUI(data: FootballLeagueModel) {
        binding.vpLeagueFragment.adapter = FootballLeagueAdapter(this, data);
        binding.tablLeagueFragment.tabMode = TabLayout.MODE_AUTO
        binding.tablLeagueFragment.tabGravity = TabLayout.GRAVITY_CENTER
        TabLayoutMediator(
            binding.tablLeagueFragment, binding.vpLeagueFragment
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = resources.getStringArray(R.array.header_tab_item_title_league)[position]
        }.attach()

//        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

//        binding.tablTeamInformationFragment.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager!!.currentItem = tab.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//
//            }
//        })
    }

    private fun setupObserver(){
        viewModel.footballLeagueList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> setupUI(it1) }
                }
                Resource.Status.ERROR ->
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.common_alert_error_title)
                        .setMessage(R.string.common_alert_error_message)
                        .setNeutralButton(R.string.common_ok) { _, _ ->  findNavController().popBackStack()}
                        .create().show()
//                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}