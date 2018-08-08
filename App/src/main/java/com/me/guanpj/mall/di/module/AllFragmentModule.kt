package com.me.guanpj.mall.di.module

import com.me.guanpj.mall.di.component.BaseFragmentComponent
import dagger.Module

@Module(subcomponents = arrayOf(BaseFragmentComponent::class))
abstract class AllFragmentModule {
}