package com.example.chatgptbotandroid.di.component

import android.content.Context
import com.example.chatgptbotandroid.ChatGptApplication
import com.example.chatgptbotandroid.data.api.NetworkService
import com.example.chatgptbotandroid.data.repo.ChatGptRepo
import com.example.chatgptbotandroid.di.ApplicationContext
import com.example.chatgptbotandroid.di.module.ApplicationModule
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