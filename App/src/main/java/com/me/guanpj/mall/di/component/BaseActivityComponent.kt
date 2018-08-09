package com.me.guanpj.mall.di.component

import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(AndroidInjectionModule::class))
interface BaseActivityComponent {

    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseMvpActivity<*>>()
}