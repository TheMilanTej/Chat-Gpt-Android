package com.milantejani.aibotandroid.ui.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.milantejani.aibotandroid.ads.APP_OPEN_FIRST_OPEN_ACTIVITY
import com.milantejani.aibotandroid.ads.AppOpenAdManagerUtils
import com.milantejani.aibotandroid.ads.SharedPreferenceAppConfigLiveData
import com.milantejani.aibotandroid.databinding.ActivitySplashBinding
import com.milantejani.aibotandroid.ui.dashboard.DashBoardActivity
import com.milantejani.aibotandroid.utils.ModelPreferencesManager
import com.milantejani.aibotandroid.utils.observeRemovable

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var activityLaunched = false

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = kotlinx.coroutines.Runnable {
        startChooseActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppOpenAdManagerUtils.setScreenFlag(APP_OPEN_FIRST_OPEN_ACTIVITY)

        var executedAdLoadScreen = false
        ModelPreferencesManager.get()?.let { prefs ->
            SharedPreferenceAppConfigLiveData(prefs = prefs).observeRemovable(this) {
                if (executedAdLoadScreen && !(isFinishing || isDestroyed)) {
                    startChooseActivity()
                    return@observeRemovable true
                }
                if (it != null) {
                    executedAdLoadScreen = true
                    if (it.appOpens?.firstOpen == true) {
                        handler.removeCallbacksAndMessages(null)
                        handler.postDelayed(runnable, 3000L)

                        AppOpenAdManagerUtils.setConfig(appOpenConfig = it.appOpens)
                        AppOpenAdManagerUtils.loadAd(this)
                        AppOpenAdManagerUtils.showWhenLoadFinished { boolean ->
                            if (!(isDestroyed || isFinishing || activityLaunched)) {
                                if (boolean) {
                                    // Ad is Loaded. we can waite
                                    handler.removeCallbacksAndMessages(null)
                                    AppOpenAdManagerUtils.showAdIfAvailable(this,
                                        onShowAdCompleteListener = object :
                                            AppOpenAdManagerUtils.OnShowAdCompleteListener {
                                            override fun onShowAdComplete() {
                                                startChooseActivity()
                                            }
                                        })
                                } else {
                                    startChooseActivity()
                                }
                            }
                        }

                    } else {
                        startChooseActivity()
                    }
                    return@observeRemovable true
                } else {
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed(runnable, 3000L)
                }
                return@observeRemovable false
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({

        }, 3000)

    }

    private fun startChooseActivity() {
        if (activityLaunched) {
            return
        }
        activityLaunched = true
        startActivity(Intent(this@SplashActivity, DashBoardActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}