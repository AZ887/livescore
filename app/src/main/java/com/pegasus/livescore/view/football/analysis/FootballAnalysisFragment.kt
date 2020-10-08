package com.pegasus.livescore.view.football.analysis

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.pegasus.livescore.R
import com.pegasus.livescore.database.entitymodel.football.FootballAnalysisModel
import com.pegasus.livescore.database.entitymodel.football.Referee
import com.pegasus.livescore.databinding.CommonVhItemImageDetailBinding
import com.pegasus.livescore.databinding.FragmentFootballAnalysisBinding
import com.pegasus.livescore.util.DateTimeUtil
import com.pegasus.livescore.util.Resource
import com.pegasus.livescore.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FootballAnalysisFragment : Fragment() {

    private var binding : FragmentFootballAnalysisBinding by autoCleared()
    private val viewModel: FootballAnalysisViewModel by viewModels()

    companion object {
        fun newInstance() = FootballAnalysisFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentFootballAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.footballAnalysisList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.list.isNullOrEmpty()) {
                        arrangeList(it.data as FootballAnalysisModel)
//                        adapter.setItems(it.data?.matchList as ArrayList<FootballMatch>)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

//                Resource.Status.LOADING ->
                //todo
//                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun arrangeList(response: FootballAnalysisModel){
        for(mainList in response.list){
            if(mainList.isNotEmpty()){
                mainList.forEach { (key, value) ->
                    filterListToView(key, value)
                }
            }
        }

            if(response.referee.isNotEmpty()){
                binding.layoutLinearAnalysisMainList.addView(addTitle(resources.getString(R.string.analysis_referee_information)))
                for(referee in response.referee){
                    filterRefereeToView(referee)
                }
            }

    }

    private fun <E> filterListToView(key: String, value: List<E>){
        var uiList : MutableList<LinearLayout> = mutableListOf()
        var title = ""
        var headerListString: MutableList<String>
        var headerListString2: MutableList<String> = mutableListOf()
        var contentList : MutableList<MutableList<String>> = mutableListOf()
        var contentList2 : MutableList<MutableList<String>> = mutableListOf()
        when(key){
            "headToHead" -> {
                title = viewModel.homeName + " " + resources.getString(R.string.analysis_vs) + " " +
                        viewModel.awayName + " - " + resources.getString(R.string.analysis_match_record_10game)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_headToHead).toMutableList()

                for (listItem in value) {
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","").split("^",",")
                    contentSubList.add(DateTimeUtil.simpleDateFormatConverter(seperated[5]) + " " + seperated[3])
                    contentSubList.add(viewModel.homeName.toString())
                    contentSubList.add(seperated[15] + " - " + seperated[16] +
                            System.getProperty("line.separator") + seperated[17] + " - " + seperated[18])
                    contentSubList.add(viewModel.awayName.toString())
                    contentList.add(contentSubList)
                    if(contentList.size >= 10){
                        break
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "homeLastMatches" -> {
                title = viewModel.homeName + " " + resources.getString(R.string.analysis_vs) +
                        " - " + resources.getString(R.string.common_home) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.analysis_recent_record_10game)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_headToHead).toMutableList()

                for (listItem in value) {
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","").split("^",",")
                    contentSubList.add(DateTimeUtil.simpleDateFormatConverter(seperated[5]) + " " + seperated[3])
                    contentSubList.add((seperated[9]))
                    contentSubList.add(seperated[15] + " - " + seperated[16] +
                            System.getProperty("line.separator") + seperated[17] + " - " + seperated[18])
                    contentSubList.add(seperated[13])
                    contentList.add(contentSubList)
                    if(contentList.size >= 10){
                        break
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "awayLastMatches" -> {
                title = viewModel.awayName + " " + resources.getString(R.string.analysis_vs) +
                        " - " + resources.getString(R.string.common_away) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.analysis_recent_record_10game)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_headToHead).toMutableList()

                for (listItem in value) {
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","").split("^",",")
                    contentSubList.add(DateTimeUtil.simpleDateFormatConverter(seperated[5]) + " " + seperated[3])
                    contentSubList.add((seperated[9]))
                    contentSubList.add(seperated[15] + " - " + seperated[16] +
                            System.getProperty("line.separator") + seperated[17] + " - " + seperated[18])
                    contentSubList.add(seperated[13])
                    contentList.add(contentSubList)
                    if(contentList.size >= 10){
                        break
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "homeSchedule" -> {
                title = viewModel.homeName + " " + resources.getString(R.string.analysis_vs) +
                        " - " + resources.getString(R.string.common_home) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.analysis_next_5_game_schedule)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_headToHead).toMutableList()
                for (listItem in value) {
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","").split("^",",")
                    contentSubList.add(DateTimeUtil.simpleDateFormatConverter(seperated[5]) + " " + seperated[3])
                    contentSubList.add((seperated[9]))
                    contentSubList.add(seperated[13])
                    contentSubList.add(seperated[15])
                    contentList.add(contentSubList)
                    if(contentList.size >= 5){
                        break
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "awaySchedule" -> {
                title = viewModel.awayName + " " + resources.getString(R.string.analysis_vs) +
                        " - " + resources.getString(R.string.common_away) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.analysis_next_5_game_schedule)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_headToHead).toMutableList()
                for (listItem in value) {
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","").split("^",",")
                    contentSubList.add(DateTimeUtil.simpleDateFormatConverter(seperated[5]) + " " + seperated[3])
                    contentSubList.add((seperated[9]))
                    contentSubList.add(seperated[13])
                    contentSubList.add(seperated[15])
                    contentList.add(contentSubList)
                    if(contentList.size >= 5){
                        break
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "homeOdds" -> {
                title = viewModel.homeName +
                        " - " + resources.getString(R.string.common_home) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.common_odds)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_odds).toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString()
                        .replace("[", "")
                        .replace("]","")
                        .split("^",",")

                    if(i == 3 || i == 7){
                        headerListString2 =
                            resources.getStringArray(R.array.header_analysis_team_odds_recent6).toMutableList()
                        var contentSubList2: MutableList<String> = mutableListOf()
                        contentSubList2.add(resources.getStringArray(R.array.header_analysis_home_team_odd)[i])
                        contentSubList2.add(seperated[1])
                        contentSubList2.add(seperated[2] + System.getProperty("line.separator") + seperated[3])
                        contentSubList2.add(seperated[4])
                        contentList2.add(contentSubList2)
                    }else{
                        contentSubList.add(resources.getStringArray(R.array.header_analysis_home_team_odd)[i])
                        contentSubList.add(seperated[1])
                        contentSubList.add(seperated[2] + "\\" + seperated[3] + "\\" + seperated[4] + "\\" + seperated[5])
                        contentSubList.add(seperated[6]+ System.getProperty("line.separator") + seperated[7])
                        contentSubList.add(seperated[8] + System.getProperty("line.separator") + seperated[9])
                        contentList.add(contentSubList)
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
                uiList.add(createLinearLayoutFromList(headerListString2, true))
                for (item in contentList2) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "awayOdds" -> {
                title = viewModel.awayName +
                        " - " + resources.getString(R.string.common_away) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.common_odds)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_odds).toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    val seperated: List<String> = listItem.toString()
                        .replace("[", "")
                        .replace("]","")
                        .split("^",",")

                    if(i == 3 || i == 7){
                        headerListString2 =
                            resources.getStringArray(R.array.header_analysis_team_odds_recent6).toMutableList()
                        var contentSubList2: MutableList<String> = mutableListOf()
                        contentSubList2.add(resources.getStringArray(R.array.header_analysis_home_team_odd)[i])
                        contentSubList2.add(seperated[1])
                        contentSubList2.add(seperated[2] + System.getProperty("line.separator") + seperated[3])
                        contentSubList2.add(seperated[4])
                        contentList2.add(contentSubList2)
                    }else{
                        contentSubList.add(resources.getStringArray(R.array.header_analysis_home_team_odd)[i])
                        contentSubList.add(seperated[1])
                        contentSubList.add(seperated[2] + "\\" + seperated[3] + "\\" + seperated[4] + "\\" + seperated[5])
                        contentSubList.add(seperated[6]+ System.getProperty("line.separator") + seperated[7])
                        contentSubList.add(seperated[8] + System.getProperty("line.separator") + seperated[9])
                        contentList.add(contentSubList)
                    }
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
                uiList.add(createLinearLayoutFromList(headerListString2, true))
                for (item in contentList2) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "homeGoals" -> {
                title = viewModel.homeName +
                        " - " + resources.getString(R.string.common_home) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.common_Goals) + "/" +
                        resources.getString(R.string.analysis_goals)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_goals).toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    if(i == 0){
                        continue
                    }
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","")
                        .split("^",",")
                    contentSubList.addAll(seperated)
                    contentSubList[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                    contentList.add(contentSubList)
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "awayGoals" -> {
                title = viewModel.awayName +
                        " - " + resources.getString(R.string.common_away) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.common_Goals) + "/" +
                        resources.getString(R.string.analysis_goals)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_goals).toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    if(i == 0){
                        continue
                    }
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","")
                        .split("^",",")
                    contentSubList.addAll(seperated)
                    contentSubList[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                    contentList.add(contentSubList)
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "homeHT" -> {
                title = viewModel.homeName +
                        " - " + resources.getString(R.string.common_home) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.analysis_halftime)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_halftime).toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    var contentSubList2: MutableList<String> = mutableListOf()

                    contentSubList.add(resources.getStringArray(R.array.header_analysis_team_half_time_wdl)[i])

                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","")
                        .split("^",",")
                    if(i == 0 || i == 1){
                        contentSubList2.addAll(seperated)
                        contentSubList2[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                        contentList2.add(contentSubList2)
                    }else{
                        contentSubList.addAll(seperated)
                        contentSubList[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                        contentList.add(contentSubList)}
                }
                uiList.add(addTitle(title))
                for (item in contentList2) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "awayHT" -> {
                title = viewModel.awayName +
                        " - " + resources.getString(R.string.common_away) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.analysis_halftime)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_halftime).toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    var contentSubList2: MutableList<String> = mutableListOf()

                    contentSubList.add(resources.getStringArray(R.array.header_analysis_team_half_time_wdl)[i])

                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","")
                        .split("^",",")
                    if(i == 0 || i == 1){
                        contentSubList2.addAll(seperated)
                        contentSubList2[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                        contentList2.add(contentSubList2)
                    }else{
                        contentSubList.addAll(seperated)
                        contentSubList[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                        contentList.add(contentSubList)}
                }
                uiList.add(addTitle(title))
                for (item in contentList2) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "homeShootTime" -> {
                title = viewModel.homeName +
                        " - " + resources.getString(R.string.common_home) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.common_Goals) +
                        resources.getString(R.string.analysis_time_statistics)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_time_statistics)
                        .toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    if(i == 0){
                        continue
                    }
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","")
                        .split("^",",")
                    contentSubList.addAll(seperated)
                    contentSubList[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]
                    contentList.add(contentSubList)
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
            "awayShootTime" -> {
                title = viewModel.awayName +
                        " - " + resources.getString(R.string.common_away) + " " +
                        resources.getString(R.string.common_team) + " " +
                        resources.getString(R.string.common_Goals) +
                        resources.getString(R.string.analysis_time_statistics)
                headerListString =
                    resources.getStringArray(R.array.header_analysis_team_time_statistics)
                        .toMutableList()

                for (i in value.indices) {
                    val listItem = value[i]
                    var contentSubList: MutableList<String> = mutableListOf()
                    if(i == 0){
                        continue
                    }
                    val seperated: List<String> = listItem.toString().replace("[", "").replace("]","")
                        .split("^",",")

                    contentSubList.addAll(seperated)
                    contentSubList[0] = resources.getStringArray(R.array.header_analysis_team_goals_time_analysis)[i]

                    contentList.add(contentSubList)
                }
                uiList.add(addTitle(title))
                uiList.add(createLinearLayoutFromList(headerListString, true))
                for (item in contentList) {
                    uiList.add(createLinearLayoutFromList(item, false))
                }
            }
        }

        for (item : LinearLayout in uiList){
            binding.layoutLinearAnalysisMainList.addView(item)
        }
    }

    private fun filterRefereeToView(referee: Referee){
        val imageDetailView = CommonVhItemImageDetailBinding.inflate(LayoutInflater.from(context))
        Glide.with(imageDetailView.root)
            .load(referee.photo)
            .placeholder(R.drawable.ic_basketball_default)
            .into(imageDetailView.ivCommonVhItem)
        for(i in resources.getStringArray(R.array.header_analysis_referee_detail).indices){

            val title = resources.getStringArray(R.array.header_analysis_referee_detail)[i]

            var linearLayout = LinearLayout(context)

            var tvtitle = TextView(context)
            tvtitle.text = title +  ":"
            var linearLayoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
            );
            linearLayoutParams.weight = 2F
            tvtitle.layoutParams = linearLayoutParams
            tvtitle.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            tvtitle.textSize = 14F
            linearLayout.addView(tvtitle)

            var tvContent = TextView(context)
            tvContent.text = " " + mapping(referee, i)
            var linearLayoutParamsTvContent = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
            );
            linearLayoutParamsTvContent.weight = 3F
            tvContent.layoutParams = linearLayoutParamsTvContent
            tvContent.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            tvtitle.textSize = 14F
            linearLayout.addView(tvContent)

            imageDetailView.lyCommonVhItem.addView(linearLayout)
        }
        binding.layoutLinearAnalysisMainList.addView(imageDetailView.root)
    }
    private fun addTitle(title: String) : LinearLayout{
        var linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        var tvTitle = TextView(context)
        tvTitle.text = title

        var tvParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );

        tvParams.setMargins(0,40,0,0)

        tvTitle.setPadding(40, 40, 40, 40)
        tvTitle.layoutParams = tvParams
        tvTitle.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.color_black, null))
        tvTitle.setTextColor(ResourcesCompat.getColor(resources, R.color.color_white, null))
        tvTitle.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tvTitle.textSize = 14F

        linearLayout.addView(tvTitle)
        return linearLayout
    }

    private fun createLinearLayoutFromList(headerListString: List<String>, isHeader: Boolean):LinearLayout{
        return createLinearLayoutFromList(headerListString, isHeader, "")
    }

    private fun createLinearLayoutFromList(headerListString: List<String>, isHeader: Boolean, specialString: String): LinearLayout{
        var linearLayout = LinearLayout(context)
        for(i in headerListString.indices){
            val  headerString = headerListString[i]
            var tvHeader = TextView(context)
            tvHeader.text = headerString
            var linearLayoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
            );
            linearLayoutParams.weight = 1F
            tvHeader.layoutParams = linearLayoutParams

            val backgroundColor : Int
            var textColor : Int
            if(isHeader){
                backgroundColor = R.drawable.common_quarter_title_border
                textColor = R.color.color_white
            }else{
                backgroundColor = R.drawable.border
                textColor = R.color.color_black
            }

            tvHeader.background = ResourcesCompat.getDrawable(
                resources,
                backgroundColor,
                null
            )
            tvHeader.setTextColor(ResourcesCompat.getColor(resources, textColor, null))

            tvHeader.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvHeader.textSize = 12F
            tvHeader.setPadding(8, 8, 8, 8)
            tvHeader.gravity = Gravity.CENTER_VERTICAL
            linearLayout.addView(tvHeader)
        }
        return linearLayout
    }

    private fun mapping(referee: Referee, index : Int) : String{
        when(index){
            0 -> return resources.getStringArray(R.array.analysis_referee_type)[referee.typeId.toInt()-1]
            1 -> return referee.nameEn
            2 -> return referee.birthday
            3 -> return referee.countryEn
        }
        return ""
    }
}