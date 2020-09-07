package com.pegasus.livescore.view.football

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pegasus.livescore.database.entitymodel.FootballMatch
import com.pegasus.livescore.databinding.FootballViewholderBinding
import kotlinx.android.synthetic.main.common_vh_item_team_logo.view.*
import kotlinx.android.synthetic.main.football_viewholder.view.*

class FootballScoreAdapter(private val listener: FootballScoreItemListener) : RecyclerView.Adapter<FootballScoreViewHolder>() {

    interface FootballScoreItemListener {
        fun onClickViewHolder(item: FootballMatch)
    }

    private val items = ArrayList<FootballMatch>()

    fun setItems(items: ArrayList<FootballMatch>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballScoreViewHolder {
        val binding: FootballViewholderBinding = FootballViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FootballScoreViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FootballScoreViewHolder, position: Int) = holder.bind(items[position])
}

class FootballScoreViewHolder(private val itemBinding: FootballViewholderBinding, private val listener: FootballScoreAdapter.FootballScoreItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var footballMatch: FootballMatch

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: FootballMatch) {
        this.footballMatch = item

        Glide.with(itemBinding.root)
            .load(item.homeLogo)
            .into(itemBinding.layoutTeam1.ivTeamLogo)
        itemBinding.layoutTeam1.tvTeamName.text = item.homeEn
        itemBinding.layoutTeam1.tvTeamScore.text = item.homeScore.toString()

        Glide.with(itemBinding.root)
            .load(item.awayLogo)
            .into(itemBinding.layoutTeam2.ivTeamLogo)
        itemBinding.layoutTeam2.tvTeamName.text = item.awayEn
        itemBinding.layoutTeam2.tvTeamScore.text = item.awayScore.toString()
    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.footballMatch)
    }
}