package com.pegasus.livescore.view.football.teaminformation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballTeamInformationModel
import com.pegasus.livescore.databinding.FootballTeamInformationFragmentBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import com.pegasus.livescore.view.football.teaminformation.adapter.FootballTeamInformationAdapter
import com.pegasus.livescore.view.football.teaminformation.viewmodel.FootballTeamInformationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballTeamInformationFragment : Fragment() {

    companion object {
        fun newInstance() = FootballTeamInformationFragment()
    }
    private var binding: FootballTeamInformationFragmentBinding by autoCleared()
    private val viewModel: FootballTeamInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FootballTeamInformationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver()
        // TODO: Use the ViewModel
    }

    private fun setupUI(data: FootballTeamInformationModel) {
        binding.vpTeamInformationFragment.adapter = FootballTeamInformationAdapter(this, data);
        binding.tablTeamInformationFragment.tabMode = TabLayout.MODE_AUTO
        binding.tablTeamInformationFragment.tabGravity = TabLayout.GRAVITY_CENTER
        TabLayoutMediator(
            binding.tablTeamInformationFragment, binding.vpTeamInformationFragment
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = resources.getStringArray(R.array.header_team_information_tablayout)[position]
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
        viewModel.footballTeamInformationList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> setupUI(it1) }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        })
    }
}
