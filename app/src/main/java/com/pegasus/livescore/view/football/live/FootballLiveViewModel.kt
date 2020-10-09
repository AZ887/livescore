package com.pegasus.livescore.view.football.live

import android.content.ContentValues
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pegasus.livescore.database.repository.FootballRepository
import androidx.lifecycle.viewModelScope
import com.pegasus.livescore.database.entitymodel.football.FootballAnalysisModel
import com.pegasus.livescore.database.entitymodel.football.FootballEventModel
import com.pegasus.livescore.database.entitymodel.football.FootballScoreModel
import com.pegasus.livescore.util.Resource
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString

class FootballLiveViewModel @ViewModelInject constructor(
    private val repository: FootballRepository,
    private val okHttpClient: OkHttpClient
) : ViewModel() {
    var footballLiveList = MutableLiveData<Resource<FootballScoreModel>>()
    var footballEventList = MutableLiveData<Resource<FootballEventModel>>()

init {
    viewModelScope.launch {
        footballLiveList.value = repository.getFootballScore()
//        footballEventList.value = repository.getFootballEvent()
        initSocket()
    }
}

    private fun initSocket(){
        var request: Request = Request.Builder().url("wss://www.77577.com:6001/app/AABBCCDD?protocol=7&client=js&version=5.1.1&flash=false").build()
        var webSocketListener: WebSocketListener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
//                webSocket.send(
//                    """{
//                        "event": "pusher:subscribe",
//                        "data": { "auth": "",
//                        "channel":"channel-live-zqbf-match-list" }
//                    }"""
//                )
                Log.e(ContentValues.TAG, "onOpen")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.e(ContentValues.TAG, "MESSAGE: $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.e(ContentValues.TAG, "MESSAGE: " + bytes.hex())
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
                webSocket.cancel()
                Log.e(ContentValues.TAG, "CLOSE: $code $reason")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.e(ContentValues.TAG, "onClosed" )
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e(ContentValues.TAG, "onFailure" )
            }
        }
        okHttpClient.newWebSocket(request,webSocketListener)
    }
}