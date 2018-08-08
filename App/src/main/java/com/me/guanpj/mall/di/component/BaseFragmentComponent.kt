package com.me.guanpj.mall.di.component

import com.me.guanpj.mall.mvp.view.fragment.BaseMvpFragment
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(AndroidInjectionModule::class))
interface BaseFragmentComponent {

    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseMvpFragment<*>>() {

    }
}