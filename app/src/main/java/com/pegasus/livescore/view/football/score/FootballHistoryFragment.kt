package com.pegasus.livescore.view.football.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FragmentFootballHistoryBinding
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballHistoryFragment : Fragment(), FootballHistoryAdapter.FootballScoreItemListener {

    private var binding: FragmentFootballHistoryBinding by autoCleared()
    private val viewModel: FootballViewModel by viewModels()
    private lateinit var adapter: FootballHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFootballHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
//        var request: Request = Request.Builder().url("wss://www.77577.com:6001/app/AABBCCDD?protocol=7&client=js&version=5.1.1&flash=false").build()
//        var webSocketListener: WebSocketListener = object : WebSocketListener() {
//            override fun onOpen(webSocket: WebSocket, response: Response) {
//                webSocket.send(
//                    """{
//    "type": "subscribe",
//    "channels": [{ "name": "channel-live-zqbf-match-list" }]
//}"""
//                )
//                Log.e(TAG, "onOpen")
//            }
//
//            override fun onMessage(webSocket: WebSocket, text: String) {
//                Log.e(TAG, "MESSAGE: $text")
//            }
//
//            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
//                Log.e(TAG, "MESSAGE: " + bytes.hex())
//            }
//
//            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//                webSocket.close(1000, null)
//                webSocket.cancel()
//                Log.e(TAG, "CLOSE: $code $reason")
//            }
//
//            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
//                Log.e(TAG, "onClosed" )
//            }
//
//            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//                Log.e(TAG, "onFailure" )
//            }
//        }
//        okHttpClient.newWebSocket(request,webSocketListener)
//        okHttpClient.dispatcher.executorService.shutdown()
    }

    private fun setupRecyclerView() {
        adapter = FootballHistoryAdapter(this)
        binding.rvFragmentFootballHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentFootballHistory.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.footballScoreList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.matchList.isNullOrEmpty()) {

                        adapter.setItems(it.data?.matchList as ArrayList<FootballMatch>)
                    }
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
//        findNavController().navigate(
//            R.id.nav_basketball_history
//            bundleOf("id" to characterId)
//        )
    }

}


