package com.me.guanpj.mall.goods.di.component

import com.me.guanpj.mall.goods.di.module.CartModule
import com.me.guanpj.mall.goods.module.cart.CartFragment
import com.me.guanpj.mall.library.di.component.FragmentComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import dagger.Component

/*
  购物车Component
 */
@ComponentScope
@Component(dependencies = arrayOf(FragmentComponent::class), modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}
