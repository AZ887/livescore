package com.pegasus.livescore.view.football.score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FootballViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.util.*
import kotlin.collections.ArrayList

class FootballHistoryAdapter(private val listener: FootballScoreItemListener) : RecyclerView.Adapter<FootballHistoryViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballHistoryViewHolder {
        val binding: FootballViewholderBinding = FootballViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FootballHistoryViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FootballHistoryViewHolder, position: Int) = holder.bind(items[position], checkIsHeader(items[position].matchTime))

    private fun checkIsHeader(datestring: String?): Boolean{;
        val date =  DateTimeUtil.stringToDateConverter(datestring)
        if(date?.get(Calendar.DAY_OF_YEAR)!=headerDay){
            headerDay = date?.get(Calendar.DAY_OF_YEAR)!!
            return true
        }
        return false
    }
}

