package com.me.guanpj.mall.pay.di.component

import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import com.me.guanpj.mall.pay.di.module.PayModule
import com.me.guanpj.mall.pay.module.PayActivity
import dagger.Component

/*
  支付Component
 */
@ComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity: PayActivity)
}
