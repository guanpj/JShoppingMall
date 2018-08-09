package com.me.guanpj.mall.goods.module.category

import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface CategoryContract {
    interface View : IBaseView {
        //获取商品分类列表
        fun onGetCategoryResult(result: MutableList<Category>?)
    }

    interface Presenter : IBasePresenter<View>
}