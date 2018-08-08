package com.me.guanpj.mall.library.di.delegate

import android.app.Application
import com.me.guanpj.mall.library.di.component.DaggerComponent
import com.me.guanpj.mall.library.di.component.DaggerDaggerComponent
import com.me.guanpj.mall.library.di.module.DaggerModule
import javax.inject.Inject

class DaggerDelegate(val mApplication: Application) {
    @Inject
    lateinit var mActivityLifecycleCallbacks: DaggerActivityLifecycleCallbacks

    lateinit var mComponent: DaggerComponent

    init {
        mComponent = DaggerDaggerComponent.builder()
                .daggerModule(DaggerModule(mApplication))
                .build()
        mComponent.inject(this)
    }
}