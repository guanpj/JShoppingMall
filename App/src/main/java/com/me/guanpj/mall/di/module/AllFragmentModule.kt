package com.me.guanpj.mall.di.module

import com.kotlin.message.ui.fragment.MessageFragment
import com.me.guanpj.mall.di.component.BaseFragmentComponent
import com.me.guanpj.mall.goods.module.cart.CartListFragment
import com.me.guanpj.mall.goods.module.cart.CartListFragmentModule
import com.me.guanpj.mall.goods.module.category.CategoryFragment
import com.me.guanpj.mall.goods.module.category.CategoryFragmentModule
import com.me.guanpj.mall.goods.module.goods.detail.GoodsDetailFragmentModule
import com.me.guanpj.mall.goods.module.goods.detail.GoodsDetailTabOneFragment
import com.me.guanpj.mall.meassage.module.MessageFragmentModule
import com.me.guanpj.mall.order.module.order.list.OrderListFragment
import com.me.guanpj.mall.order.module.order.list.OrderListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = arrayOf(BaseFragmentComponent::class))
abstract class AllFragmentModule {

    @ContributesAndroidInjector(modules = [CartListFragmentModule::class])
    abstract fun contributesCartListFragmentInjector(): CartListFragment

    @ContributesAndroidInjector(modules = [CategoryFragmentModule::class])
    abstract fun contributesCategoryFragmentInjector(): CategoryFragment

    @ContributesAndroidInjector(modules = [GoodsDetailFragmentModule::class])
    abstract fun contributesGoodsDetailFragmentInjector(): GoodsDetailTabOneFragment


    @ContributesAndroidInjector(modules = [MessageFragmentModule::class])
    abstract fun contributesMessageFragmentInjector(): MessageFragment


    @ContributesAndroidInjector(modules = [OrderListFragmentModule::class])
    abstract fun contributesOrderListFragmentInjector(): OrderListFragment
}