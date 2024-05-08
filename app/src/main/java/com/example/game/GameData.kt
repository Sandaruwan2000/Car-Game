package com.example.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameData:ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _speed = MutableLiveData<Int>()
    val speed: LiveData<Int>
        get() = _speed

    init {
        _score.value = 0
        _speed.value = 0
    }

    fun updateScore(newScore: Int) {
        _score.value = newScore
    }

    fun updateSpeed(newSpeed: Int) {
        _speed.value = newSpeed
    }

}