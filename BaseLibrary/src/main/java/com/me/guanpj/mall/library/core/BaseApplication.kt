package com.me.guanpj.mall.library.core

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.me.guanpj.mall.library.di.component.AppComponent
import com.me.guanpj.mall.library.di.component.DaggerAppComponent
import com.me.guanpj.mall.library.di.module.AppModule
import com.me.guanpj.mall.library.util.AppPrefsUtils

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPrefsUtils.init(this)
        AppStatusTracker.init(this)

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        instance = this

        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

    companion object {
        lateinit var instance: Application
        lateinit var appComponent: AppComponent
    }
}