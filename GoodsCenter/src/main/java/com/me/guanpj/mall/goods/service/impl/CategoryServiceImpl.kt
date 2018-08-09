package com.kotlin.goods.service.impl

import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.goods.data.repository.CategoryRepository
import com.me.guanpj.mall.goods.service.CategoryService
import com.me.guanpj.mall.library.ext.convert
import rx.Observable
import javax.inject.Inject

/*
  商品分类 业务层 实现类
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {
    @Inject
    lateinit var repository: CategoryRepository

    /*
      获取商品分类列表
     */
    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }
}
