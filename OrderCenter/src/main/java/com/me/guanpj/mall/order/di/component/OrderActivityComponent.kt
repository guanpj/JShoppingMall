package com.me.guanpj.mall.order.di.component

import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import com.me.guanpj.mall.order.di.module.OrderModule
import com.me.guanpj.mall.order.module.order.confirm.OrderConfirmActivity
import com.me.guanpj.mall.order.module.order.detail.OrderDetailActivity
import dagger.Component

/*
  订单Component
 */
@ComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderActivityComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(activity: OrderDetailActivity)
}
