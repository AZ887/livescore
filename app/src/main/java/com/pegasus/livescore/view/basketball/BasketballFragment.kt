package com.pegasus.livescore.view.basketball

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.databinding.FragmentBasketballBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class BasketballFragment : Fragment(), BasketballScoreAdapter.BasketballScoreItemListener {

    private var binding: FragmentBasketballBinding by autoCleared()
    private val viewModel: BasketballViewModel by viewModels()
    private lateinit var adapter: BasketballScoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = BasketballScoreAdapter(this)
        binding.rvFragmentFootball.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvFragmentBasketball.layoutManager = object : LinearLayoutManager(requireContext()) {
//            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
//                lp.height = height / 4
//                return true
//            }
//        }
        binding.rvFragmentFootball.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.basketballScoreList.observe(viewLifecycleOwner, Observer {
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

    override fun onClickViewHolder(item: BasketballMatch) {
        TODO("Not yet implemented")
    }
}