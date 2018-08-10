package com.me.guanpj.mall.library.di.component

import android.app.Activity
import android.content.Context
import com.me.guanpj.mall.library.di.module.FragmentModule
import com.me.guanpj.mall.library.di.module.LifecycleProviderModule
import com.me.guanpj.mall.library.di.scope.FragmentScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class, LifecycleProviderModule::class])
interface FragmentComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}