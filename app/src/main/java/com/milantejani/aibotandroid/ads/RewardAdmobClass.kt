package com.milantejani.aibotandroid.ads

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.milantejani.aibotandroid.BuildConfig
import com.milantejani.aibotandroid.R
import com.milantejani.aibotandroid.utils.CustomDialogHelper
import com.milantejani.aibotandroid.utils.isConnected
import java.lang.ref.WeakReference

object RewardAdmobClass {

    private const val TAG = "Admob_Reward"
    private const val waitingTimeForAd = 8000L
    private const val adFailedAttempts = 3
    private const val adLoadAuto = true

    private var isRewardShown = false
    private var admobRewardAd: RewardedAd? = null
    private var adFailedCounter = 0
    private var isAdLoaded = false
    private var rewardConfig: AppConfig.Rewarded? = null


    private fun isAdLoaded() = (isAdLoaded && (admobRewardAd != null))

    fun setConfig(
        mContext: WeakReference<Context>, rewarded: AppConfig.Rewarded?
    ) {
        rewardConfig = rewarded
        checkConfig(mContext)
    }

    private fun checkConfig(mContext: WeakReference<Context>) {

        val wContext = mContext.get() ?: return

        loadRewardAd(wContext) {}
    }

    fun loadRewardAd(
        mContext: Context, listener: (Boolean) -> Unit
    ) {

        /*if(mContext.isSubscribed()){
            listener.invoke(false)
            return
        }*/

        if (isAdLoaded()) {
            return
        }

        val adRequest = AdRequest.Builder().build()

        val adId = if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/5224354917"
        } else {
            rewardConfig?.adId ?: mContext.getString(R.string.ADS_REWARD_ID)
        }

        RewardedAd.load(mContext, adId, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(ad: LoadAdError) {
                Log.e(TAG, "onAdFailedToLoad - $ad")
                admobRewardAd = null
                isAdLoaded = false
                isRewardShown = false
                adFailedCounter++
                if (adFailedCounter < adFailedAttempts) {
                    loadRewardAd(mContext) {}
                }
                listener.invoke(false)
            }

            override fun onAdLoaded(ad: RewardedAd) {
                admobRewardAd = ad
                isAdLoaded = true
                isRewardShown = false
                Log.e(TAG, "Loaded")
                listener.invoke(true)
            }

        })
    }

    fun showRewardAd(
        activity: Activity, listener: (Boolean) -> Unit, listenerImp: (() -> Unit)? = null
    ) {
        var isUserEarnedReward = false
        admobRewardAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.e(TAG, "onAdDismissedFullScreenContent")
                admobRewardAd = null
                isAdLoaded = false
                isRewardShown = false
                if (adLoadAuto) {
                    loadRewardAd(activity) {}
                }
                activity.runOnUiThread {
                    if (isUserEarnedReward) {
                        listener.invoke(true)
                    }
                }
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                Log.e(TAG, "onAdDismissedFullScreenContent")
                isAdLoaded = false
                isRewardShown = false
                super.onAdFailedToShowFullScreenContent(p0)
                activity.runOnUiThread { listener.invoke(false) }
            }

            override fun onAdImpression() {
                super.onAdImpression()
                isRewardShown = true
                listenerImp?.invoke()
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()

                isRewardShown = true
            }
        }

        if (isAdLoaded) {
            admobRewardAd?.show(activity) {
                isUserEarnedReward = true
            }
        } else {
            activity.runOnUiThread { listener.invoke(false) }
        }
    }

    fun loadAndShowReward(
        activity: Activity,
        flag: Int,
        dialog: CustomDialogHelper? = null,
        listener: (Boolean) -> Unit
    ) {

        /*if(activity.isSubscribed()){
            listener.invoke(false)
            return
        }*/

        if (rewardConfig?.rewardedAds?.contains(flag) != true) {
            listener.invoke(false)
            return
        }

        var isTimeUp = false
        var isAdShow = false
        if (activity.isConnected()) {
            afterDelay(waitingTimeForAd) {
                if (!activity.isDestroyed && !activity.isFinishing) if (dialog?.isShowing() == true) {
                    try {
                        dialog.dismiss()
                    } catch (e: IllegalArgumentException) {
                        // Do nothing.
                    } catch (e: Exception) {
                        // Do nothing.
                    }
                }
                isTimeUp = true
                if (!isAdShow) activity.runOnUiThread { listener.invoke(false) }
            }
            Log.e(TAG, "isAdLoaded ${isAdLoaded()}")
            if (isAdLoaded()) {
                Log.e(TAG, "Already Loaded")
                if (!activity.isDestroyed && !activity.isFinishing) if (dialog?.isShowing() == true) {
                    try {
                        dialog.dismiss()
                    } catch (e: IllegalArgumentException) {
                        // Do nothing.
                    } catch (e: Exception) {
                        // Do nothing.
                    }
                }
                if (!isTimeUp) showRewardAd(activity, {
                    isAdShow = true
                    activity.runOnUiThread { listener.invoke(it) }
                }, {
                    isAdShow = true
                })
            } else {
                dialog?.show()
                loadRewardAd(activity) {
                    Log.e(TAG, "Load Ad")
                    if (!activity.isDestroyed && !activity.isFinishing) if (dialog?.isShowing() == true) {
                        try {
                            dialog.dismiss()
                        } catch (e: IllegalArgumentException) {
                            // Do nothing.
                        } catch (e: Exception) {
                            // Do nothing.
                        }
                    }
                    if (!isTimeUp) showRewardAd(activity, {
                        Log.e(TAG, "isAdShown $it")
                        activity.runOnUiThread { listener.invoke(it) }
                    }, {
                        isAdShow = true
                    })
                }
            }
        } else {
            activity.runOnUiThread { listener.invoke(false) }
        }
    }

    private fun afterDelay(delayMillis: Long, block: () -> Unit) {
        val handler =
            Handler(Looper.getMainLooper()) // Use the main thread's looper for UI-related tasks
        handler.postDelayed({
            block.invoke()
        }, delayMillis)
    }
}