package com.me.guanpj.mall.library.di.component

import android.app.Application
import com.me.guanpj.mall.library.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun application(): Application
}