package com.me.guanpj.mall.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val application: Application) {

    @Singleton
    @Provides
    fun provideApplictaion() : Application = application
}