package com.me.guanpj.mall.goods.data.repository

import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.goods.data.GetCategoryReq
import com.me.guanpj.mall.goods.data.api.CategoryApi
import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.library.data.net.RetrofitFactory
import rx.Observable
import javax.inject.Inject

/*
    商品分类 数据层
 */
class CategoryRepository @Inject constructor(){
    /*
        获取商品分类
     */
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }

}
