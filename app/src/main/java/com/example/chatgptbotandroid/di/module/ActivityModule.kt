package com.example.chatgptbotandroid.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatgptbotandroid.data.repo.ChatGptRepo
import com.example.chatgptbotandroid.di.ActivityContext
import com.example.chatgptbotandroid.ui.base.ViewModelProviderFactory
import com.example.chatgptbotandroid.ui.chat.ChatGptViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideChatGptViewModel(chatGptRepository: ChatGptRepo): ChatGptViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(ChatGptViewModel::class) {
            ChatGptViewModel(chatGptRepository)
        })[ChatGptViewModel::class.java]
    }


}