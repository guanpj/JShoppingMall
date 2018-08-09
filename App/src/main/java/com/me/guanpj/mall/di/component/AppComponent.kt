package com.me.guanpj.mall.di.component

import com.me.guanpj.mall.core.MyApplication
import com.me.guanpj.mall.di.module.AppModule
import com.me.guanpj.mall.library.di.component.DaggerComponent
import com.me.guanpj.mall.di.module.AllActivityModule
import com.me.guanpj.mall.di.module.AllFragmentModule
import com.me.guanpj.mall.di.module.LifecycleProviderModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

/*@Component(dependencies = [DaggerComponent::class],
        modules = [AllActivityModule::class, AllFragmentModule::class, AppModule::class, LifecycleProviderModule::class])*/
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AllActivityModule::class,
    AllFragmentModule::class,
    AppModule::class,
    LifecycleProviderModule::class])
interface AppComponent {
    fun inject(application: MyApplication)
}