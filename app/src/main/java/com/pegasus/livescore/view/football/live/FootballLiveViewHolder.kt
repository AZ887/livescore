package com.pegasus.livescore.view.football.live

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballMatch
import com.pegasus.livescore.databinding.FootballLiveViewholderBinding
import com.pegasus.livescore.util.DateTimeUtil
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.format.TextStyle

class FootballLiveViewHolder(private val itemBinding: FootballLiveViewholderBinding, private val listener: FootballLiveAdapter.FootballLiveItemListener) : RecyclerView.ViewHolder(itemBinding.root),
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
            .into(itemBinding.layoutLeftTeamDetail.ivTeamLogo)

        itemBinding.layoutLeftTeamDetail.tvTeamName.text = item.homeEn

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
            .placeholder(R.drawable.ic_football_default)
            .into(itemBinding.layoutRightTeamDetail.ivTeamLogo)
        itemBinding.layoutRightTeamDetail.tvTeamName.text = item.awayEn

        itemBinding.layoutRightTeamDetail.root.tag = "awayTeam"
        itemBinding.layoutRightTeamDetail.root.setOnClickListener(this)

        itemBinding.layoutFootballPreScoreDetail.tvFootballCorner.text = "C : 1-6"
        itemBinding.layoutFootballPreScoreDetail.tvFootballHt.text = "HT : 0-0"
        addButton(itemBinding, item)
    }

    override fun onClick(v: View) {
        if(v.tag != null){
            listener.onClickViewHolder(v.tag.toString(), this.footballMatch)
        }
    }

    private fun addButton(itemBinding: FootballLiveViewholderBinding, item: FootballMatch){
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
        if(item.havEvent){
            itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_event_text)))
        }
        if(item.havTextLive){
            itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_textlive_text)))
        }
        if(item.havPlayerDetails){
            itemBinding.layoutBottomButton.addView(createButton(resources.getString(R.string.live_button_player_detail_text)))
        }
    }

    private fun createButton(title: String): TextView{
        val context = itemBinding.root.context
        val button = TextView(context)
        button.text = title
        button.textSize = 12F
        button.tag = title
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

        button.tag = title
        button.setOnClickListener(this)
        return button
    }
}