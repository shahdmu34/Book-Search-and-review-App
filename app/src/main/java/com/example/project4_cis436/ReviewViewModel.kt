package com.example.project4_cis436

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReviewViewModel : ViewModel() {
    private val userInput = MutableLiveData<String>()

    fun setUserInput(input: String) {
        userInput.value = input
    }

    fun getUserInput(): LiveData<String> {
        return userInput
    }


}