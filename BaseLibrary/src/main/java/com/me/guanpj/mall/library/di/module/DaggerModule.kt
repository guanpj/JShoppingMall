package com.me.guanpj.mall.library.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaggerModule(private val mApplication: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = this.mApplication
}
