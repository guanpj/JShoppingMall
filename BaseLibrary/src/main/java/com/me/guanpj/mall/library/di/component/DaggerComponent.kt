package com.me.guanpj.mall.library.di.component

import android.app.Application
import com.me.guanpj.mall.library.di.delegate.DaggerDelegate
import com.me.guanpj.mall.library.di.module.DaggerModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, DaggerModule::class))
interface DaggerComponent {
    fun application(): Application

    fun inject(daggerDelegate: DaggerDelegate)
}
