package com.milantejani.aibotandroid.ads

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.milantejani.aibotandroid.BuildConfig
import com.milantejani.aibotandroid.R
import java.util.Date


object AppOpenAdManagerUtils {
    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    private var isShowingAd = false

    private var loadTime: Long = 0
    private var appOpenConfig: AppConfig.AppOpen? = null
    private var screenFlag = 0

    fun setScreenFlag(flag: Int) {
        screenFlag = flag
    }


    fun setConfig(appOpenConfig: AppConfig.AppOpen) {
        AppOpenAdManagerUtils.appOpenConfig = appOpenConfig
    }

    fun isShowingAd() = isShowingAd

    private var returnCallBack: ((isAdLoaded: Boolean) -> Unit)? = null
    fun showWhenLoadFinished(returnCallBack: ((isAdLoaded: Boolean) -> Unit)) {
        if (isLoadingAd) {
            AppOpenAdManagerUtils.returnCallBack = returnCallBack
        } else if (isAdAvailable()) {
            returnCallBack(true)
        } else {
            returnCallBack(false)
        }
    }

    fun cancelWhenLoadFinished() {
        returnCallBack = null
    }

    fun loadAd(context: Activity) {
        // Do not load ad if there is an unused ad or one is already loading.

        /*if (context.isSubscribed()) {
            returnCallBack?.invoke(true)
            returnCallBack = null
            return
        }*/

        if (isLoadingAd || isAdAvailable()) {
            return
        }

        isLoadingAd = true
        val request = AdRequest.Builder().build()
        val adId = if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/3419835294"
        } else {
            appOpenConfig?.adId ?: context.getString(R.string.ADS_APP_OPEN_ID)
        }
        AppOpenAd.load(context, adId, request, object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                isLoadingAd = false
                loadTime = Date().time
                returnCallBack?.invoke(true)
                returnCallBack = null
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {

                isLoadingAd = false
                returnCallBack?.invoke(false)
                returnCallBack = null
            }
        })
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference: Long = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private fun isAdAvailable(): Boolean {
        // Ad references in the app open beta will time out after four hours, but this time limit
        // may change in future beta versions. For details, see:
        // https://support.google.com/admob/answer/9341964?hl=en
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    // add in all activity except
    fun showAdIfAvailable(activity: Activity) {
        if (appOpenConfig?.appOpenAd?.contains(screenFlag) == true) {
            showAdIfAvailable(activity, object : OnShowAdCompleteListener {
                override fun onShowAdComplete() {
                    // Empty because the user will go back to the activity that shows the ad.
                }
            })
        }
    }

    fun showAdIfAvailable(activity: Activity, onShowAdCompleteListener: OnShowAdCompleteListener) {
        // If the app open ad is already showing, do not show the ad again.
        /*if (activity.isSubscribed()) {
            onShowAdCompleteListener.onShowAdComplete()
            return
        }*/

        if (isShowingAd) {
            return
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        if (!isAdAvailable()) {
            onShowAdCompleteListener.onShowAdComplete()
            loadAd(activity)
            return
        }

        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {

            override fun onAdDismissedFullScreenContent() {
                // Set the reference to null so isAdAvailable() returns false.
                appOpenAd = null
                isShowingAd = false
                onShowAdCompleteListener.onShowAdComplete()
                loadAd(activity)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                appOpenAd = null
                isShowingAd = false
                onShowAdCompleteListener.onShowAdComplete()
                loadAd(activity)
            }

            override fun onAdShowedFullScreenContent() {
            }
        }
        isShowingAd = true
        appOpenAd?.show(activity) ?: onShowAdCompleteListener.onShowAdComplete()
    }

    interface OnShowAdCompleteListener {
        fun onShowAdComplete()
    }
}