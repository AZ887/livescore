package com.pegasus.livescore.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.pegasus.livescore.util.Resource.Status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

suspend fun <A> performGetOperation(networkCall: suspend () -> Resource<A>) : Resource<A>{

         val responseStatus = networkCall.invoke()
         if (responseStatus.status == SUCCESS) {

         } else if (responseStatus.status == ERROR) {

         }
    return responseStatus
 }
