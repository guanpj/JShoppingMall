package com.me.guanpj.mall.goods.module.goods

import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface GoodListContract {
    interface View : IBaseView {
        //获取商品列表
        fun onGetGoodsListResult(result: MutableList<Goods>?)
    }

    interface Presenter : IBasePresenter<View>
}