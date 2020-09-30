package com.pegasus.livescore.view.basketball.score

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.databinding.BasketballViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BasketballHistoryAdapter(private val listener: BasketballHistoryItemListener) : RecyclerView.Adapter<BasketballHistoryViewHolder>() {

    interface BasketballHistoryItemListener {
        fun onClickViewHolder(item: BasketballMatch)
    }

    private val items = ArrayList<BasketballMatch>()
    private var headerDay : Int = 0

    fun setItems(items: ArrayList<BasketballMatch>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketballHistoryViewHolder {
        val binding: BasketballViewholderBinding = BasketballViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BasketballHistoryViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BasketballHistoryViewHolder, position: Int) = holder.bind(items[position], checkIsHeader(items[position].matchTime))

    fun checkIsHeader(datestring: String?): Boolean{
        val date =  DateTimeUtil.stringToDateConverter(datestring)
        if(date?.get(Calendar.DAY_OF_YEAR)!=headerDay){
            headerDay = date?.get(Calendar.DAY_OF_YEAR)!!
            return true
        }
        return false
    }
}