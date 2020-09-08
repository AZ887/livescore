package com.pegasus.livescore.view.basketball

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.databinding.BasketballViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class BasketballScoreAdapter(private val listener: BasketballScoreItemListener) : RecyclerView.Adapter<BasketballScoreViewHolder>() {

    interface BasketballScoreItemListener {
        fun onClickViewHolder(item: BasketballMatch)
    }

    private val items = ArrayList<BasketballMatch>()

    fun setItems(items: ArrayList<BasketballMatch>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketballScoreViewHolder {
        val binding: BasketballViewholderBinding = BasketballViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BasketballScoreViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BasketballScoreViewHolder, position: Int) = holder.bind(items[position])
}

class BasketballScoreViewHolder(private val itemBinding: BasketballViewholderBinding, private val listener: BasketballScoreAdapter.BasketballScoreItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var BasketballMatch: BasketballMatch

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: BasketballMatch) {
        this.BasketballMatch = item

        val date = DateTimeUtil.stringToDateConverter(item.matchTime)
        itemBinding.layoutHeader.llHeader.setBackgroundColor(Color.parseColor(item?.color))
        itemBinding.layoutHeader.tvHeaderDetail.text = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(date)

        Glide.with(itemBinding.root)
            .load(item.homeLogo)
            .into(itemBinding.layoutTeam1.ivTeamLogo)
        itemBinding.layoutTeam1.tvTeamName.text = item.homeTeamEn
        itemBinding.layoutTeam1.tvTeamScore.text = item.homeScore.toString()

        Glide.with(itemBinding.root)
            .load(item.awayLogo)
            .into(itemBinding.layoutTeam2.ivTeamLogo)
        itemBinding.layoutTeam2.tvTeamName.text = item.awayTeamEn
        itemBinding.layoutTeam2.tvTeamScore.text = item.awayScore.toString()
    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.BasketballMatch)
    }
}