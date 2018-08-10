package com.me.guanpj.mall.core

import cn.jpush.android.api.JPushInterface
import com.me.guanpj.mall.library.core.BaseApplication

class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}