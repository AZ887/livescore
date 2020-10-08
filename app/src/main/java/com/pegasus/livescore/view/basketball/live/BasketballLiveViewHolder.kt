package com.pegasus.livescore.view.basketball.live

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.basketball.BasketballMatch
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.BasketballLiveViewholderBinding
import com.pegasus.livescore.databinding.FootballLiveViewholderBinding
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
            .placeholder(R.drawable.ic_basketball_default)
            .into(itemBinding.layoutLeftTeamDetail.ivTeamLogo)

        itemBinding.layoutLeftTeamDetail.tvTeamName.text = item.homeTeamEn
        itemBinding.layoutLeftTeamDetail.root.tag = "homeTeam"

        itemBinding.layoutLeftTeamDetail.root.setOnClickListener(this)

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
            .placeholder(R.drawable.ic_basketball_default)
            .into(itemBinding.layoutRightTeamDetail.ivTeamLogo)
        itemBinding.layoutRightTeamDetail.tvTeamName.text = item.awayTeamEn

        itemBinding.layoutLeftTeamDetail.root.tag = "awayTeam"

        itemBinding.layoutLeftTeamDetail.root.setOnClickListener(this)

        itemBinding.layoutBasketballQuarterDetail.tvTeamTitle.tvTitle.text = ""
        itemBinding.layoutBasketballQuarterDetail.tvTeamTitle.tvTeamScore1.text = "Home"
        itemBinding.layoutBasketballQuarterDetail.tvTeamTitle.tvTeamScore2.text = "Away"

        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore1.tvTitle.text = "Q1"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore1.tvTeamScore1.text = "999"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore1.tvTeamScore2.text = "999"

        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore2.tvTitle.text = "Q2"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore2.tvTeamScore1.text = "999"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore2.tvTeamScore2.text = "999"

        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore3.tvTitle.text = "Q3"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore3.tvTeamScore1.text = "999"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore3.tvTeamScore2.text = "999"

        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore4.tvTitle.text = "Q4"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore4.tvTeamScore1.text = "999"
        itemBinding.layoutBasketballQuarterDetail.tvTeamQuarterScore4.tvTeamScore2.text = "999"

        itemBinding.layoutBasketballQuarterDetail.tvTeamFinalScore.tvTitle.text = "F"
        itemBinding.layoutBasketballQuarterDetail.tvTeamFinalScore.tvTeamScore1.text = "999"
        itemBinding.layoutBasketballQuarterDetail.tvTeamFinalScore.tvTeamScore2.text = "999"

        addButton(itemBinding, item)
    }

    override fun onClick(v: View?) {
        listener.onClickViewHolder(this.basketballMatch)
    }

    private fun addButton(itemBinding: BasketballLiveViewholderBinding, item: BasketballMatch){
        val resources = itemBinding.root.resources
        itemBinding.layoutBottomButton.removeAllViews()
        if(item.havBriefing){
            itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_briefing_text)))
        }
        itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_analysis_text)))

        itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_league_text)))

        if(item.havLineup){
            itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_lineup_text)))
        }
        if(item.havLiveText){
            itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_textlive_text)))
        }
    }

    private fun createButton(title: String): TextView {
        val context = itemBinding.root.context
        val button = TextView(context)
        button.text = title
        button.textSize = 12F
        var linearLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.setMargins(20,20,20,20)
        button.layoutParams = linearLayoutParams
        button.setPadding(40,20,40,20)
        button.setLines(1)
        button.textAlignment = View.TEXT_ALIGNMENT_CENTER

        button.background = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.border_sport_detail_button,
            null
        )

        button.setTextColor(ResourcesCompat.getColor(context.resources, R.color.color_sport_detail_button, null))
        return button
    }
}