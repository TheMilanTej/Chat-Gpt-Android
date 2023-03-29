package com.example.chatgptbotandroid

import android.app.Application
import com.example.chatgptbotandroid.di.component.ApplicationComponent
import com.example.chatgptbotandroid.di.component.DaggerApplicationComponent
import com.example.chatgptbotandroid.di.module.ApplicationModule

class
ChatGptApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}