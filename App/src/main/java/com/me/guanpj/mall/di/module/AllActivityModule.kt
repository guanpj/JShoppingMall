package com.me.guanpj.mall.di.module

import com.me.guanpj.mall.di.component.BaseActivityComponent
import com.me.guanpj.mall.user.di.module.LoginActivityModule
import com.me.guanpj.mall.user.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = arrayOf(BaseActivityComponent::class))
abstract class AllActivityModule {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributesLoginActivityInjector(): LoginActivity
}