package com.me.guanpj.mall.goods.module.goods.detail

import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface GoodsDetailContract {
    interface View : IBaseView {
        //获取商品详情
        fun onGetGoodsDetailResult(result: Goods)
        //加入购物车
        fun onAddCartResult(result: Int)
    }

    interface Presenter : IBasePresenter<View>
}