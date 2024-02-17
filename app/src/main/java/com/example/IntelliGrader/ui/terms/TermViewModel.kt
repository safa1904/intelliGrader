package com.example.IntelliGrader.ui.terms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TermViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Term Fragment"
    }
    val text: LiveData<String> = _text
}