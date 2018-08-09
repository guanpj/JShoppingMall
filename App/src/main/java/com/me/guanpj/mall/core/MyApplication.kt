package com.me.guanpj.mall.core

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import com.me.guanpj.mall.di.component.AppComponent
import com.me.guanpj.mall.di.module.AppModule
import com.me.guanpj.mall.library.core.AppStatusTracker
import com.me.guanpj.mall.library.di.delegate.DaggerDelegate
import com.me.guanpj.mall.library.util.AppPrefsUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class MyApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var mAppComponent: AppComponent
    lateinit var mDaggerDalegate: DaggerDelegate

    override fun onCreate() {
        super.onCreate()
        AppPrefsUtils.init(this)
        AppStatusTracker.init(this)
        mDaggerDalegate = DaggerDelegate(this)

        mAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        mAppComponent.inject(this)

        context = this

        /*ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)*/
    }

    companion object {
        lateinit var context: Context
    }

    override fun activityInjector() = mActivityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector
}