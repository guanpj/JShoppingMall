package com.me.guanpj.mall.library.di.delegate

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerFragmentLifecycleCallbacks @Inject constructor() : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
        super.onFragmentAttached(fm, f, context)
        AndroidSupportInjection.inject(f)
    }
}