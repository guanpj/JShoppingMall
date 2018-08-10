package com.kotlin.goods.injection.component

import com.me.guanpj.mall.goods.di.module.CartModule
import com.me.guanpj.mall.goods.di.module.GoodsModule
import com.me.guanpj.mall.goods.module.goods.list.GoodsListActivity
import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import dagger.Component

/*
  商品Component
 */
@ComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsActivityComponent {
    fun inject(activity: GoodsListActivity)
}
