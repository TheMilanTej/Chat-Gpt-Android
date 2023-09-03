package com.milantejani.aibotandroid.di.component

import android.content.Context
import com.milantejani.aibotandroid.ChatGptApplication
import com.milantejani.aibotandroid.data.api.NetworkService
import com.milantejani.aibotandroid.data.repo.ChatGptRepo
import com.milantejani.aibotandroid.di.ApplicationContext
import com.milantejani.aibotandroid.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: ChatGptApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getChatGptRepository(): ChatGptRepo

}