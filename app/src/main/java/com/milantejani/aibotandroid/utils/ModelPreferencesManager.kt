package com.milantejani.aibotandroid.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.gson.GsonBuilder

/**
 * Singleton class for managing preferences for POJO or model class's object.
 * @author Malwinder Singh
 * medium.com/@dev.malwinder
 */
object ModelPreferencesManager {

    //Shared Preference field used to save and retrieve JSON string
    lateinit var preferences: SharedPreferences

    //Name of Shared Preference file
    const val PREFERENCES_FILE_NAME = "ADS_PREFERENCES"

    /**
     * Call this first before retrieving or saving object.
     *
     * @param application Instance of application class
     */
    fun with(application: Application) {
        preferences = application.getSharedPreferences(
            PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun get() = if(this::preferences.isInitialized) preferences else null
    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        preferences.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}

fun <T> LiveData<T>.observeRemovable(lifecycleOwner: LifecycleOwner, callback: (T?) -> Boolean) {
    this.observe(lifecycleOwner, object : Observer<T?> {
        override fun onChanged(t: T?) {
            val isReadyForRemove = callback(t)
            if (isReadyForRemove) {
                removeObserver(this)
            }
        }
    })
}