package com.milantejani.aibotandroid.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

fun Context.isConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun Activity.isConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun Any.showLog(logData: () -> Pair<String, String>) {
    val (tag, message) = logData()
    Log.e(tag, message)
}