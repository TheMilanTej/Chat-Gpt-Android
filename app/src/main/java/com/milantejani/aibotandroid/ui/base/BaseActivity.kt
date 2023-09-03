package com.milantejani.aibotandroid.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.milantejani.aibotandroid.BuildConfig
import com.milantejani.aibotandroid.R
import com.milantejani.aibotandroid.ads.AdContextCallBack
import com.milantejani.aibotandroid.ads.AppConfig
import com.milantejani.aibotandroid.ads.InterstitialAdsUtils
import com.milantejani.aibotandroid.utils.CustomDialogHelper

class BaseActivity : AppCompatActivity() {
    private var mAdView: AdView? = null

    var customDialogGeneral: CustomDialogHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customDialogGeneral = CustomDialogHelper(this@BaseActivity)
        customDialogGeneral?.create(R.layout.loading_square, onCancelListener = {

        }).apply {

        }
        customDialogGeneral?.cancelOnTouch(false)
    }

    fun loadBannerAds(
        adContainerView: LinearLayout, banner: AppConfig.Banner, flag: Int
    ) {

        //banner ads if internet is on than it will show
        val hasBannerAd = banner.bannerAds != null && (flag in banner.bannerAds)

        val bannerId = if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/6300978111"
        } else {
            banner.adId ?: getString(R.string.ADS_APP_BANNER_ID)
        }

        if (hasBannerAd) {
            adContainerView.visibility = View.VISIBLE
            mAdView = AdView(this)
            mAdView?.adUnitId = bannerId

            adContainerView.removeAllViews()
            adContainerView.addView(mAdView)

            val adSize = getAdSize(this)
            mAdView?.setAdSize(adSize)

            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }
    }

    private fun getAdSize(activity: Activity): AdSize {
        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val widthPixels = outMetrics.widthPixels.toFloat()
        val density = outMetrics.density
        val adWidth = (widthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdView?.destroy()
        customDialogGeneral?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        InterstitialAdsUtils.adCallback = object : AdContextCallBack {
            override fun getContext(): Context? {
                return if (!isDestroyed && !isFinishing) {
                    this@BaseActivity
                } else {
                    null
                }
            }
        }
        /*if (isSubscribed()) {
            mAdView?.destroy()
        } else {
        }*/
        mAdView?.resume()
    }

    override fun onPause() {
        super.onPause()
        mAdView?.pause()
    }
}