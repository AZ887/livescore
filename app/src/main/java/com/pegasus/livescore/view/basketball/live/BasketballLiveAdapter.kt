package com.pegasus.livescore.view.basketball.live

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.databinding.BasketballLiveViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.util.*
import kotlin.collections.ArrayList

class BasketballLiveAdapter(private val listener: BasketballLiveItemListener) : RecyclerView.Adapter<BasketballLiveViewHolder>() {

    interface BasketballLiveItemListener {
        fun onClickViewHolder(item: BasketballMatch)
    }

    private val items = ArrayList<BasketballMatch>()
    private var headerDay : Int = 0

    fun setItems(items: ArrayList<BasketballMatch>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketballLiveViewHolder {
        val binding: BasketballLiveViewholderBinding = BasketballLiveViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BasketballLiveViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BasketballLiveViewHolder, position: Int) = holder.bind(items[position], checkIsHeader(items[position].matchTime))

    fun checkIsHeader(datestring: String?): Boolean{
        val date =  DateTimeUtil.stringToDateConverter(datestring)
        if(date?.get(Calendar.DAY_OF_YEAR)!=headerDay){
            headerDay = date?.get(Calendar.DAY_OF_YEAR)!!
            return true
        }
        return false
    }
}