package com.pegasus.livescore.view.football

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FootballViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

    fun bind(item: FootballMatch) {
        this.footballMatch = item

        val date = DateTimeUtil.stringToDateConverter(item.matchTime)
        itemBinding.layoutHeader.llHeader.setBackgroundColor(Color.parseColor(item?.color))
        itemBinding.layoutHeader.tvHeaderDetail.text = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(date)

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