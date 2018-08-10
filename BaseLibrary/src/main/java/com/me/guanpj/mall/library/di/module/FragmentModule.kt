package com.me.guanpj.mall.library.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.me.guanpj.mall.library.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideActivity(): Activity {
        return fragment.activity!!
    }

    @FragmentScope
    @Provides
    fun provideContext(): Context {
        return fragment.context!!
    }
}