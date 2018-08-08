package com.me.guanpj.mall.di.component

import com.me.guanpj.mall.core.MyApplication
import com.me.guanpj.mall.di.module.AppModule
import com.me.guanpj.mall.library.di.component.DaggerComponent
import com.me.guanpj.mall.di.module.AllActivityModule
import com.me.guanpj.mall.di.module.AllFragmentModule
import dagger.Component

@Component(dependencies = [DaggerComponent::class],
        modules = [AllActivityModule::class, AllFragmentModule::class, AppModule::class])
interface AppComponent {
    fun inject(application: MyApplication)
}