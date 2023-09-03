package com.milantejani.aibotandroid.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.milantejani.aibotandroid.data.repo.ChatGptRepo
import com.milantejani.aibotandroid.di.ActivityContext
import com.milantejani.aibotandroid.ui.base.ViewModelProviderFactory
import com.milantejani.aibotandroid.ui.chat.ChatGptViewModel
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