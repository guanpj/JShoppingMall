package com.me.guanpj.mall.library.di.component

import android.app.Activity
import android.content.Context
import com.me.guanpj.mall.library.di.module.ActivityModule
import com.me.guanpj.mall.library.di.module.LifecycleProviderModule
import com.me.guanpj.mall.library.di.scope.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, LifecycleProviderModule::class])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}