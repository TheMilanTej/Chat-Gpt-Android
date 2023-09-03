package com.milantejani.aibotandroid.ads

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.milantejani.aibotandroid.BuildConfig
import com.milantejani.aibotandroid.R
import com.milantejani.aibotandroid.utils.PrefUtils
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

interface AdContextCallBack {
    fun getContext(): Context?
}

object InterstitialAdsUtils {

    private var mInterstitialAd: InterstitialAd? = null
    private var interstitialConfig: AppConfig.Interstitial? = null
    private var isLoadingAd = false

    private var actionCount = 0
    private var adShowing = 0
    var adCallback: AdContextCallBack? = null

    fun setConfig(
        mContext: WeakReference<Context>,
        interstitial: AppConfig.Interstitial?
    ) {
        interstitialConfig = interstitial
        checkConfig(mContext = mContext)
    }

    private fun checkConfig(mContext: WeakReference<Context>) {
        val context = mContext.get() ?: return
//            Check Installed Time
        val timeNow = System.currentTimeMillis()
        if (timeNow >= (PrefUtils.getFromPrefs(
                context,
                PrefUtils.IA_CLICK_SHOW_AD_AFTER_X_MINTS,
                0L
            ) as Long)
        ) {
//                Show Ad
            if (adShowing < (interstitialConfig?.maximumShowing ?: Int.MAX_VALUE)) {
                // Instant Request
                loadGoogleInterstitialAd(context = context)
            } else {
                // No Request until this session not finished
            }
        } else {
//            Dont Show Ad
//            if want to request after this delay
            val delayTime = PrefUtils.getFromPrefs(
                context,
                PrefUtils.IA_CLICK_SHOW_AD_AFTER_X_MINTS,
                0L
            ) as Long - timeNow
            if (delayTime >= 0) {
                autoLoad(minimumTimeToLoadSeconds = delayTime)
            }
        }
    }

    private fun loadGoogleInterstitialAd(context: Context) {

        /*if(context.isSubscribed()){
            return
        }*/

        if (mInterstitialAd != null) {
            return
        }
        if (isLoadingAd) {
            return
        }
        isLoadingAd = true
        val adRequest = AdRequest.Builder().build()
        val adId = if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/1033173712"
        } else {
            interstitialConfig?.adId ?: context.getString(R.string.ADS_APP_FULL_SCR_ID)
        }
        AdManagerInterstitialAd.load(
            context,
            adId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    mInterstitialAd = interstitialAd
                    isLoadingAd = false
                }

                override fun onAdFailedToLoad(interstitialAd: LoadAdError) {
                    super.onAdFailedToLoad(interstitialAd)

                    mInterstitialAd = null
                    isLoadingAd = false
                }
            })
    }

    private val handler: Handler = Handler(Looper.getMainLooper())

    private fun autoLoad(
        minimumTimeToLoadSeconds: Long = (interstitialConfig?.minimumTimeToLoadSeconds
            ?: 0).toLong()
    ) {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            adCallback?.getContext()?.let { context ->
                loadGoogleInterstitialAd(context = context)
            }
        }, TimeUnit.SECONDS.toMillis(minimumTimeToLoadSeconds))
    }

    // We are not showing ads When PickActivity
    fun showInterstitialAd(
        activity: AppCompatActivity,
        flag: Int,
        runnable: Runnable
    ) {

        /*if(activity.isSubscribed()){
            runnable.run()
            return
        }*/

        if ((actionCount % (interstitialConfig?.betweenActions ?: 3)) != 0) {
            actionCount++
            runnable.run()
            return
        }

        if (interstitialConfig?.interstitialAds?.contains(flag) != true) {
//          if AdAction is ready to show. we will not increase here
//          actionCount++
            runnable.run()
            return
        }

        if (mInterstitialAd != null) {
            val ifClickedShowAdAfterInLongs = TimeUnit.MINUTES.toMillis(
                interstitialConfig?.ifClickedShowAdAfterInMints?.toLong() ?: 0L
            )

            var isAdShown = false
            activity.lifecycleScope.launchWhenResumed {
                if(isAdShown) {

                }
                isAdShown = false
            }
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    isAdShown = true
                    actionCount++
                    adShowing++
                    //Add Logic here, to prevent at least two EVENT CALLBACK
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    //Toast.makeText(activity, "Dismissed", Toast.LENGTH_SHORT).show()
                    mInterstitialAd = null
                    runnable.run()
                    checkConfig(mContext = WeakReference(activity))
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    super.onAdFailedToShowFullScreenContent(adError)
                    mInterstitialAd = null
                    checkConfig(mContext = WeakReference(activity))
                    runnable.run()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    PrefUtils.saveToPrefs(
                        activity,
                        PrefUtils.IA_CLICK_SHOW_AD_AFTER_X_MINTS,
                        System.currentTimeMillis() + ifClickedShowAdAfterInLongs
                    )
                }
            }
            mInterstitialAd?.show(activity) ?: runnable.run()
        } else {
            runnable.run()
        }
    }


}