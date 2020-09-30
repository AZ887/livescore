package com.pegasus.livescore.view.basketball.live

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.databinding.BasketballLiveViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.text.SimpleDateFormat

class BasketballLiveViewHolder(private val itemBinding: BasketballLiveViewholderBinding, private val listener: BasketballLiveAdapter.BasketballLiveItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var basketballMatch: BasketballMatch

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: BasketballMatch, isHeader:Boolean) {
        this.basketballMatch = item

        val date = DateTimeUtil.stringToDateConverter(item.matchTime)

        itemBinding.layoutSectionHeader.llHeader.visibility = if(isHeader || this.adapterPosition == 0) View.VISIBLE else View.GONE
        itemBinding.layoutSectionHeader.tvSectionHeader.text = SimpleDateFormat("yyyy/MM/dd").format(date?.time).toString()

        itemBinding.layoutHeader.llHeader.setBackgroundColor(Color.parseColor(item.color))
        itemBinding.layoutHeader.tvHeaderLeague.text = item.leagueEn
        itemBinding.layoutHeader.tvHeaderDetail.text = SimpleDateFormat("HH:mm").format(date?.time).toString()

        Glide.with(itemBinding.root)
            .load(item.homeLogo)
            .placeholder(R.drawable.ic_football_default)
            .into(itemBinding.layoutLeftTeamDetail.ivTeamLogo)

        itemBinding.layoutLeftTeamDetail.tvTeamName.text = item.homeTeamEn

        itemBinding.layoutMatchLiveStatus.tvMatchCurrentStatus.text = "FT"
        itemBinding.layoutMatchLiveStatus.tvMatchLatestScore.text = "90"
        itemBinding.layoutMatchLiveStatus.tvMatchOdd1.text = "0.99"
        itemBinding.layoutMatchLiveStatus.tvMatchOdd2.text = "0.99"
        itemBinding.layoutMatchLiveStatus.tvMatchOdd3.text = "0.99"
        itemBinding.layoutMatchLiveStatus.tvMatchOdd4.text = "0.99"
        itemBinding.layoutMatchLiveStatus.tvMatchOdd5.text = "0.99"
        itemBinding.layoutMatchLiveStatus.tvMatchOdd6.text = "0.99"


        Glide.with(itemBinding.root)
            .load(item.awayLogo)
            .placeholder(R.drawable.ic_football_default)
            .into(itemBinding.layoutRightTeamDetail.ivTeamLogo)
        itemBinding.layoutRightTeamDetail.tvTeamName.text = item.awayTeamEn
    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.basketballMatch)
    }
}