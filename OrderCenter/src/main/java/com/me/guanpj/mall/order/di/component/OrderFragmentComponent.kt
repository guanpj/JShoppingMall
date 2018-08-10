package com.me.guanpj.mall.order.di.component

import com.me.guanpj.mall.library.di.component.FragmentComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import com.me.guanpj.mall.order.di.module.OrderModule
import com.me.guanpj.mall.order.module.order.list.OrderListFragment
import dagger.Component

/*
  订单Component
 */
@ComponentScope
@Component(dependencies = arrayOf(FragmentComponent::class), modules = arrayOf(OrderModule::class))
interface OrderFragmentComponent {
    fun inject(fragment: OrderListFragment)
}
