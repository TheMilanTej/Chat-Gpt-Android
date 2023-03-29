package com.example.chatgptbotandroid.di.component

import com.example.chatgptbotandroid.ui.chat.MainActivity
import com.example.chatgptbotandroid.di.ActivityScope
import com.example.chatgptbotandroid.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}