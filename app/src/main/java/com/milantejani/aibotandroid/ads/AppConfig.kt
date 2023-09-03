package com.milantejani.aibotandroid.ads

import androidx.annotation.Keep
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

const val NATIVE_WHEN_BACK = 1
const val NATIVE_LOADING = 2

const val APP_OPEN_FIRST_OPEN_ACTIVITY = -1 // always disable

const val APP_OPEN_SPLASH = 1
const val APP_OPEN_MAIN_ACT = 2
const val APP_OPEN_EFFECT_LIST = 3
const val APP_OPEN_BEFORE_AFTER = 4
const val APP_OPEN_SHARE = 5
const val APP_OPEN_SETTING = 6
const val APP_OPEN_ENHANCE_PHOTO = 7
const val APP_OPEN_MY_CREATION = 8

const val BANNER_MAIN_ACT = 1
const val BANNER_EFFECT_LIST = 2
const val BANNER_BEFORE_AFTER = 3
const val BANNER_SHARE = 4
const val BANNER_SETTING = 5
const val BANNER_ENHANCE_PHOTO = 6
const val BANNER_MY_CREATION = 7

const val IN_MAIN_ACT = 1
const val IN_EFFECT_LIST = 2
const val IN_BEFORE_AFTER = 3
const val IN_SHARE = 4
const val IN_SETTING = 5
const val IN_ENHANCE_PHOTO = 6
const val IN_MY_CREATION = 7

const val REWARD_REMOVE_WATERMARK=1
const val REWARD_PREVIEW=2

@Keep
data class AppConfig(
    @SerializedName("banner") val banners: Banner?,
    @SerializedName("native") val natives: Native?,
    @SerializedName("interstitial") val interstitials: Interstitial?,
    @SerializedName("reward") val reward: Rewarded?,
    @SerializedName("appOpen") val appOpens: AppOpen?,
    @SerializedName("appVersion") val appVersion: AppVersion?,
    @SerializedName("ad") val ad: String? = null
) {
    @Keep
    data class Rewarded(@SerializedName("rewardedAd") val rewardedAds: Array<Int>? = arrayOf(),
                        @SerializedName("betweenActions") val betweenActions: Int?,
                        @SerializedName("ifClickedShowAdAfterInMints") val ifClickedShowAdAfterInMints: Int?,
                        @SerializedName("maximumShowing") val maximumShowing: Int?,
                        @SerializedName("minimumTimeToLoadSeconds") val minimumTimeToLoadSeconds: Int?,
                        @SerializedName("showAfterMaximumShowingInMints") val showAfterMaximumShowingInMints: Int?,
                        @SerializedName("adId") val adId: String?,){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Rewarded

            if (rewardedAds != null) {
                if (other.rewardedAds == null) return false
                if (!rewardedAds.contentEquals(other.rewardedAds)) return false
            } else if (other.rewardedAds != null) return false
            if (betweenActions != other.betweenActions) return false
            if (ifClickedShowAdAfterInMints != other.ifClickedShowAdAfterInMints) return false
            if (maximumShowing != other.maximumShowing) return false
            if (minimumTimeToLoadSeconds != other.minimumTimeToLoadSeconds) return false
            if (showAfterMaximumShowingInMints != other.showAfterMaximumShowingInMints) return false
            if (adId != other.adId) return false

            return true
        }

        override fun hashCode(): Int {
            var result = rewardedAds?.contentHashCode() ?: 0
            result = 31 * result + (betweenActions ?: 0)
            result = 31 * result + (ifClickedShowAdAfterInMints ?: 0)
            result = 31 * result + (maximumShowing ?: 0)
            result = 31 * result + (minimumTimeToLoadSeconds ?: 0)
            result = 31 * result + (showAfterMaximumShowingInMints ?: 0)
            result = 31 * result + (adId?.hashCode() ?: 0)
            return result
        }


    }

    @Keep
    data class AppVersion(
        @SerializedName("disabled") val disabled: Array<Int> = emptyArray(),
        @SerializedName("enable") val enable: Array<Int> = emptyArray()
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AppVersion) return false

            if (!disabled.contentEquals(other.disabled)) return false
            if (!enable.contentEquals(other.enable)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = disabled.contentHashCode()
            result = 31 * result + enable.contentHashCode()
            return result
        }
    }

    @Keep
    data class Banner(
        @SerializedName("ads") val bannerAds: Array<Int>? = arrayOf(),
        @SerializedName("adId") val adId: String?,
    ) {
        override fun toString(): String {
            return Gson().toJson(this)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Banner) return false

            if (bannerAds != null) {
                if (other.bannerAds == null) return false
                if (!bannerAds.contentEquals(other.bannerAds)) return false
            } else if (other.bannerAds != null) return false

            return true
        }

        override fun hashCode(): Int {
            return bannerAds?.contentHashCode() ?: 0
        }
    }

    @Keep
    data class Interstitial(
        @SerializedName("ads") val interstitialAds: Array<Int>? = arrayOf(),
        @SerializedName("betweenActions") val betweenActions: Int?,
        @SerializedName("ifClickedShowAdAfterInMints") val ifClickedShowAdAfterInMints: Int?,
        @SerializedName("maximumShowing") val maximumShowing: Int?,
        @SerializedName("minimumTimeToLoadSeconds") val minimumTimeToLoadSeconds: Int?,
        @SerializedName("showAfterMaximumShowingInMints") val showAfterMaximumShowingInMints: Int?,
        @SerializedName("adId") val adId: String?,
    ) {
        override fun toString(): String {
            return Gson().toJson(this)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Interstitial) return false

            if (interstitialAds != null) {
                if (other.interstitialAds == null) return false
                if (!interstitialAds.contentEquals(other.interstitialAds)) return false
            } else if (other.interstitialAds != null) return false
            if (betweenActions != other.betweenActions) return false
            if (ifClickedShowAdAfterInMints != other.ifClickedShowAdAfterInMints) return false
            if (maximumShowing != other.maximumShowing) return false
            if (minimumTimeToLoadSeconds != other.minimumTimeToLoadSeconds) return false
            if (showAfterMaximumShowingInMints != other.showAfterMaximumShowingInMints) return false

            return true
        }

        override fun hashCode(): Int {
            var result = interstitialAds?.contentHashCode() ?: 0
            result = 31 * result + (betweenActions ?: 0)
            result = 31 * result + (ifClickedShowAdAfterInMints ?: 0)
            result = 31 * result + (maximumShowing ?: 0)
            result = 31 * result + (minimumTimeToLoadSeconds ?: 0)
            result = 31 * result + (showAfterMaximumShowingInMints ?: 0)
            return result
        }
    }

    @Keep
    data class AppOpen(
        @SerializedName("ads") val appOpenAd: Array<Int>? = arrayOf(),
        @SerializedName("firstOpen") val firstOpen: Boolean?,
        @SerializedName("adId") val adId: String?,
    ) {
        override fun toString(): String {
            return Gson().toJson(this)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AppOpen) return false

            if (appOpenAd != null) {
                if (other.appOpenAd == null) return false
                if (!appOpenAd.contentEquals(other.appOpenAd)) return false
            } else if (other.appOpenAd != null) return false
            if (firstOpen != other.firstOpen) return false

            return true
        }

        override fun hashCode(): Int {
            var result = appOpenAd?.contentHashCode() ?: 0
            result = 31 * result + (firstOpen?.hashCode() ?: 0)
            return result
        }
    }

    @Keep
    data class Native(
        @SerializedName("nativeAd") val nativeAds: Array<Int>? = arrayOf(),
        @SerializedName("adId") val adId: String?,
    ) {
        override fun toString(): String {
            return Gson().toJson(this)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Native) return false

            if (nativeAds != null) {
                if (other.nativeAds == null) return false
                if (!nativeAds.contentEquals(other.nativeAds)) return false
            } else if (other.nativeAds != null) return false

            return true
        }

        override fun hashCode(): Int {
            return nativeAds?.contentHashCode() ?: 0
        }
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}