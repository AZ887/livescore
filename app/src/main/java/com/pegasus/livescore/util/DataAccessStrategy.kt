package com.pegasus.livescore.util

suspend fun <A> performGetOperation(networkCall: suspend () -> Resource<A>) : Resource<A>{

    //         if (responseStatus.status == SUCCESS) {
//
//         } else if (responseStatus.status == ERROR) {
//
//         }
    return networkCall.invoke()
 }
