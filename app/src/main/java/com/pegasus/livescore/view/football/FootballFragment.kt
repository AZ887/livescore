package com.pegasus.livescore.view.football

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pegasus.livescore.database.entitymodel.FootballMatch
import com.pegasus.livescore.databinding.FragmentFootballBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballFragment : Fragment(), FootballScoreAdapter.FootballScoreItemListener {

    private var binding: FragmentFootballBinding by autoCleared()
    private val viewModel: FootballViewModel by viewModels()
    private lateinit var adapter: FootballScoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFootballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = FootballScoreAdapter(this)
        binding.rvFragmentFootball.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentFootball.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

//                Resource.Status.LOADING ->
                    //todo
//                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickViewHolder(item: FootballMatch) {
        TODO("Not yet implemented")
        //        findNavController().navigate(
//            R.id.action_charactersFragment_to_characterDetailFragment,
//            bundleOf("id" to characterId)
//        )
    }
}