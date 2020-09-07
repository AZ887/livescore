package com.pegasus.livescore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pegasus.livescore.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveFragment : Fragment() {

    private lateinit var liveViewModel: LiveViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        liveViewModel =
            ViewModelProviders.of(this).get(LiveViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_live, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        liveViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}