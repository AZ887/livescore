package com.pegasus.livescore.view.football

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pegasus.livescore.R
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
    private var headerDay : Int = 0

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

    override fun onBindViewHolder(holder: FootballScoreViewHolder, position: Int) = holder.bind(items[position], checkIsHeader(items[position].matchTime))

    private fun checkIsHeader(datestring: String?): Boolean{
        val date =  DateTimeUtil.stringToDateConverter(datestring)
        if(date?.get(Calendar.DAY_OF_YEAR)!=headerDay){
            headerDay = date?.get(Calendar.DAY_OF_YEAR)!!
            return true
        }
        return false
    }
}

class FootballScoreViewHolder(private val itemBinding: FootballViewholderBinding, private val listener: FootballScoreAdapter.FootballScoreItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var footballMatch: FootballMatch

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: FootballMatch, isHeader:Boolean) {
        this.footballMatch = item

        val date = DateTimeUtil.stringToDateConverter(item.matchTime)
        itemBinding.layoutSectionHeader.llHeader.visibility = if(isHeader || this.adapterPosition == 0) View.VISIBLE else View.GONE
        itemBinding.layoutSectionHeader.tvSectionHeader.text = SimpleDateFormat("yyyy/MM/dd").format(date?.time).toString()

        itemBinding.layoutHeader.llHeader.setBackgroundColor(Color.parseColor(item.color))
        itemBinding.layoutHeader.tvHeaderLeague.text = item.leagueEn
        itemBinding.layoutHeader.tvHeaderDetail.text = SimpleDateFormat("HH:mm").format(date?.time).toString()

        Glide.with(itemBinding.root)
            .load(item.homeLogo)
            .placeholder(R.drawable.ic_football_default)
            .into(itemBinding.layoutTeam1.ivTeamLogo)
            
        itemBinding.layoutTeam1.tvTeamName.text = item.homeEn
        itemBinding.layoutTeam1.tvTeamScore.text = item.homeScore.toString()

        Glide.with(itemBinding.root)
            .load(item.awayLogo)
            .placeholder(R.drawable.ic_football_default)
            .into(itemBinding.layoutTeam2.ivTeamLogo)
        itemBinding.layoutTeam2.tvTeamName.text = item.awayEn
        itemBinding.layoutTeam2.tvTeamScore.text = item.awayScore.toString()
    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.footballMatch)
    }
}