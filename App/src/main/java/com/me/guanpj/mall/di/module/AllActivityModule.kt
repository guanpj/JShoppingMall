package com.me.guanpj.mall.di.module

import com.me.guanpj.mall.di.component.BaseActivityComponent
import com.me.guanpj.mall.user.module.login.LoginActivity
import com.me.guanpj.mall.user.module.login.LoginActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = arrayOf(BaseActivityComponent::class))
abstract class AllActivityModule {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributesLoginActivityInjector(): LoginActivity
}