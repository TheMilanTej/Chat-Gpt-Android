plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner

    }

    viewBinding.isEnabled = true

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )

            resValue("string", "APP_ADS_ID", "ca-app-pub-9275714082229531~3496437395")
            resValue("string", "APP_BANNER_ADS_ID", "ca-app-pub-9275714082229531/5982321317")
            resValue("string", "APP_FULL_SCR_ID", "ca-app-pub-9275714082229531/4451717417")
            resValue("string", "APP_NATIVE_ID", "ca-app-pub-9275714082229531/6662947786")
            resValue("string", "APP_APP_OPEN_ID", "ca-app-pub-9275714082229531/1938887101")
            resValue("string", "APP_REWARD_ID", "ca-app-pub-9275714082229531/5562189927")
        }
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )

            resValue("string", "APP_ADS_ID", "ca-app-pub-3940256099942544~3347511713")
            resValue("string", "APP_BANNER_ADS_ID", "ca-app-pub-3940256099942544/6300978111")
            resValue("string", "APP_FULL_SCR_ID", "ca-app-pub-3940256099942544/1033173712")
            resValue("string", "APP_NATIVE_ID", "ca-app-pub-3940256099942544/2247696110")
            resValue("string", "APP_APP_OPEN_ID", "ca-app-pub-3940256099942544/3419835294")
            resValue("string", "APP_REWARD_ID", "ca-app-pub-3940256099942544/5224354917")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ("1.8")
    }
}

dependencies {

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.activityKtx)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.espressoCore)

    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonRetrofitConverter)

    implementation(Dependencies.viewModel)
    implementation(Dependencies.liveData)
    implementation(Dependencies.runtimeKtx)

    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

    implementation(Dependencies.lottie)
    implementation(Dependencies.stfalconImageViewer)
}