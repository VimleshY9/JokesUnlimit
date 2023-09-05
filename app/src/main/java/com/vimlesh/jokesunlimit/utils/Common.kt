package com.vimlesh.jokesunlimit.utils

import java.util.Timer
import java.util.TimerTask

object Common {
    fun fetchAJokeInInterval(timer: Timer, fetchAJoke: () -> Unit) {
        timer.schedule(object : TimerTask() {
            override fun run() {
                fetchAJoke()
            }
        }, 0, Constants.TIME_INTERVAL)
    }
}