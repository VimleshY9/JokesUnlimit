package com.vimlesh.jokesunlimit.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}