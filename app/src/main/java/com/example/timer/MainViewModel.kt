package com.example.timer

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var selectedTime = ""
    var doingList = mutableListOf<String>()
    var timeList = mutableListOf<Int>()
    var sum = 0
    var newHour = 0
    var newMinute = 0
}