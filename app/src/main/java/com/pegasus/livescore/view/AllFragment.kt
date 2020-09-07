package com.pegasus.livescore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pegasus.livescore.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private lateinit var allViewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allViewModel =
            ViewModelProviders.of(this).get(AllViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        return root
    }
}
