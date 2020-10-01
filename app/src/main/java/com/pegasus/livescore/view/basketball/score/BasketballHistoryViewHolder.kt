package com.pegasus.livescore.view.basketball.score

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.databinding.BasketballViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import java.text.SimpleDateFormat

class BasketballHistoryViewHolder(private val itemBinding: BasketballViewholderBinding, private val listener: BasketballHistoryAdapter.BasketballHistoryItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var basketballMatch: BasketballMatch

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: BasketballMatch, isHeader:Boolean) {
        this.basketballMatch = item

        val date = DateTimeUtil.stringToDateConverter(item.matchTime)
        itemBinding.layoutSectionHeader.tvSectionHeader.text = SimpleDateFormat("yyyy/MM/dd").format(date?.time).toString()
        itemBinding.layoutSectionHeader.llHeader.visibility = if(isHeader || this.adapterPosition == 0) View.VISIBLE else View.GONE

        itemBinding.layoutHeader.llHeader.setBackgroundColor(Color.parseColor(item.color))
        itemBinding.layoutHeader.tvHeaderLeague.text = item.leagueEn
        itemBinding.layoutHeader.tvHeaderDetail.text = SimpleDateFormat("HH:mm").format(date?.time).toString()

        Glide.with(itemBinding.root)
            .load(item.homeLogo)
            .placeholder(R.drawable.ic_basketball_default)
            .transform(CircleCrop())
            .into(itemBinding.layoutTeam1.ivTeamLogo)
        itemBinding.layoutTeam1.tvTeamName.text = item.homeTeamEn
        itemBinding.layoutTeam1.tvTeamScore.text = item.homeScore.toString()

        Glide.with(itemBinding.root)
            .load(item.awayLogo)
            .placeholder(R.drawable.ic_basketball_default)
            .transform(CircleCrop())
            .into(itemBinding.layoutTeam2.ivTeamLogo)
        itemBinding.layoutTeam2.tvTeamName.text = item.awayTeamEn
        itemBinding.layoutTeam2.tvTeamScore.text = item.awayScore.toString()

    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.basketballMatch)
    }
}