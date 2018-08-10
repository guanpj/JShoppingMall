package com.me.guanpj.mall.library.di.module

import android.app.Activity
import android.content.Context
import com.me.guanpj.mall.library.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }

    @ActivityScope
    @Provides
    fun provideContext(): Context {
        return this.activity.applicationContext
    }
}