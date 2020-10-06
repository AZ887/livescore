package com.pegasus.livescore.view.football.live

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FootballLiveViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.util.*
import kotlin.collections.ArrayList

class FootballLiveAdapter(private val listener: FootballLiveItemListener) : RecyclerView.Adapter<FootballLiveViewHolder>() {

    interface FootballLiveItemListener {
        fun onClickViewHolder(id: Int, item: FootballMatch)
    }

    private val items = ArrayList<FootballMatch>()
    private var headerDay : Int = 0

    fun setItems(items: ArrayList<FootballMatch>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballLiveViewHolder {
        val binding: FootballLiveViewholderBinding = FootballLiveViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FootballLiveViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FootballLiveViewHolder, position: Int) = holder.bind(items[position], checkIsHeader(items[position].matchTime))

    private fun checkIsHeader(datestring: String?): Boolean{
        val date =  DateTimeUtil.stringToDateConverter(datestring)
        if(date?.get(Calendar.DAY_OF_YEAR)!=headerDay){
            headerDay = date?.get(Calendar.DAY_OF_YEAR)!!
            return true
        }
        return false
    }
}