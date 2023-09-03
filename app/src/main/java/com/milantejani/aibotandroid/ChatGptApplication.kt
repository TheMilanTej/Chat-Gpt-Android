package com.milantejani.aibotandroid

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdActivity
import com.milantejani.aibotandroid.ads.AppConfig
import com.milantejani.aibotandroid.ads.AppOpenAdManagerUtils
import com.milantejani.aibotandroid.di.component.ApplicationComponent
import com.milantejani.aibotandroid.di.component.DaggerApplicationComponent
import com.milantejani.aibotandroid.di.module.ApplicationModule
import com.milantejani.aibotandroid.utils.AppConstant
import com.milantejani.aibotandroid.utils.ModelPreferencesManager
import com.milantejani.aibotandroid.utils.showLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ChatGptApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    var applicationScope = MainScope()


    override fun onCreate() {
        super.onCreate()

        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
        ModelPreferencesManager.with(this@ChatGptApplication)
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
        ProcessLifecycleOwner.get().lifecycle.addObserver(defaultLifecycleObserver)

        applicationScope.launch(Dispatchers.IO) {
            val appConfig = AppConfig(
                banners = AppConfig.Banner(
                    bannerAds = arrayOf(
                        1, 2, 3, 4, 5, 6, 7
                    ), adId = getString(R.string.ADS_APP_BANNER_ID)
                ), natives = AppConfig.Native(
                    nativeAds = arrayOf(1, 2), adId = getString(R.string.ADS_APP_NATIVE_ID)
                ), interstitials = AppConfig.Interstitial(
                    interstitialAds = arrayOf(1, 2, 3, 4, 5, 6, 7),
                    adId = getString(R.string.ADS_APP_FULL_SCR_ID),
                    betweenActions = 2,
                    ifClickedShowAdAfterInMints = 10,
                    maximumShowing = null,
                    minimumTimeToLoadSeconds = 0,
                    showAfterMaximumShowingInMints = 10,
                ), reward = AppConfig.Rewarded(
                    rewardedAds = arrayOf(1),
                    adId = getString(R.string.ADS_APP_NATIVE_ID),
                    betweenActions = 1,
                    ifClickedShowAdAfterInMints = null,
                    maximumShowing = null,
                    minimumTimeToLoadSeconds = 3,
                    showAfterMaximumShowingInMints = 10,
                ), appOpens = AppConfig.AppOpen(
                    appOpenAd = arrayOf(1, 2, 3, 4, 5, 6, 7, 8),
                    firstOpen = true,
                    adId = getString(R.string.ADS_APP_OPEN_ID)
                ), appVersion = AppConfig.AppVersion(
                    disabled = arrayOf(), enable = arrayOf(BuildConfig.VERSION_CODE)
                ), ad = null
            )
            ModelPreferencesManager.put(appConfig, AppConstant.ADS_DATA_KEY_V2)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationScope.cancel("onLowMemory")
        applicationScope = MainScope()
    }

    private var currentActivity: Activity? = null
    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivityCreated"
            }
        }

        override fun onActivityStarted(activity: Activity) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivityStarted"
            }
            if (!AppOpenAdManagerUtils.isShowingAd()) {
                currentActivity = activity
            }
        }

        override fun onActivityResumed(activity: Activity) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivityResumed"
            }
        }

        override fun onActivityPaused(activity: Activity) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivityPaused"
            }
        }

        override fun onActivityStopped(activity: Activity) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivityStopped"
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivitySaveInstanceState"
            }
        }

        override fun onActivityDestroyed(activity: Activity) {
            showLog {
                "appClass" to "${activity::class.java.simpleName}-onActivityDestroyed"
            }
        }

    }

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            currentActivity?.let {
                if (it !is AdActivity /*&& it.isNotSubscribed()*/) {
                    AppOpenAdManagerUtils.showAdIfAvailable(it)
                }
            }
        }
    }
}