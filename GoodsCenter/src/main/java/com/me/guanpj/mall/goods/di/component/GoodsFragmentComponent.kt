package com.kotlin.goods.injection.component

import com.me.guanpj.mall.goods.di.module.CartModule
import com.me.guanpj.mall.goods.di.module.GoodsModule
import com.me.guanpj.mall.goods.module.goods.detail.GoodsDetailTabOneFragment
import com.me.guanpj.mall.library.di.component.FragmentComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import dagger.Component

/*
  商品Component
 */
@ComponentScope
@Component(dependencies = arrayOf(FragmentComponent::class), modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsFragmentComponent {
    fun inject(fragment: GoodsDetailTabOneFragment)
}
