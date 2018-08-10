package com.kotlin.goods.injection.component

import com.me.guanpj.mall.goods.di.module.CategoryModule
import com.me.guanpj.mall.goods.module.category.CategoryFragment
import com.me.guanpj.mall.library.di.component.FragmentComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import dagger.Component

/*
  商品分类Component
 */
@ComponentScope
@Component(dependencies = arrayOf(FragmentComponent::class), modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}
