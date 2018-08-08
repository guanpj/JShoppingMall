package com.me.guanpj.mall.library.di.delegate

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DaggerActivityLifecycleCallbacks @Inject constructor() : Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var mFragmentLifecycleCallbacks: DaggerFragmentLifecycleCallbacks

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        if ((activity is HasSupportFragmentInjector || activity?.application is HasSupportFragmentInjector) && activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks, true)
        }
    }

    override fun onActivityPaused(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResumed(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStarted(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityDestroyed(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStopped(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}