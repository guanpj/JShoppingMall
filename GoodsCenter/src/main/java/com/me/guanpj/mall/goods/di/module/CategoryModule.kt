package com.me.guanpj.mall.goods.di.module

import com.kotlin.goods.service.impl.CategoryServiceImpl
import com.me.guanpj.mall.goods.service.CategoryService
import dagger.Module
import dagger.Provides

/*
  商品分类Module
 */
@Module
class CategoryModule {

    @Provides
    fun provideCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}
