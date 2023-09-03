package com.milantejani.aibotandroid.ads

import android.content.SharedPreferences
import com.google.gson.Gson
import com.milantejani.aibotandroid.utils.AppConstant

class SharedPreferenceAppConfigLiveData(private val prefs: SharedPreferences) :
    SharedPreferenceLiveData<AppConfig>(prefs, AppConstant.ADS_DATA_KEY_V2, null) {

    override fun getValueFromPreferences(key: String, defValue: AppConfig?): AppConfig? {
        return Gson().fromJson(prefs.getString(key, null), AppConfig::class.java)
    }
}