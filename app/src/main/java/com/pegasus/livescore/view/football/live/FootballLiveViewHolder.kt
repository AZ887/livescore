package com.pegasus.livescore.view.football.live

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FootballLiveViewholderBinding

class FootballLiveViewHolder(private val itemBinding: FootballLiveViewholderBinding, private val listener: FootballLiveAdapter.FootballLiveItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var footballMatch: FootballMatch

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: FootballMatch, isHeader:Boolean) {

    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.footballMatch)
    }
}