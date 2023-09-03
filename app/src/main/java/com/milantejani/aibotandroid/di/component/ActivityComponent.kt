package com.milantejani.aibotandroid.di.component

import com.milantejani.aibotandroid.ui.chat.MainActivity
import com.milantejani.aibotandroid.di.ActivityScope
import com.milantejani.aibotandroid.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}