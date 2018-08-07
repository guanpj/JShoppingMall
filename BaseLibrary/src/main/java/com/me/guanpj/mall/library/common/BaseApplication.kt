package com.me.guanpj.mall.library.common

import android.app.Application
import android.content.Context
import com.me.guanpj.mall.library.util.AppStatusTracker

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppStatusTracker.init(this)
        context = this
        performInject()

        /*ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)*/
    }

    private fun performInject() {

    }

    companion object {
        lateinit var context: Context
    }
}