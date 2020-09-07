package com.pegasus.livescore.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

}