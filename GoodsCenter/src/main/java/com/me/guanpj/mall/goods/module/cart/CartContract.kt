package com.me.guanpj.mall.goods.module.cart

import com.me.guanpj.mall.goods.data.CartGoods
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface CartContract {
    interface View : IBaseView {
        //获取购物车列表
        fun onGetCartListResult(result: MutableList<CartGoods>?)
        //删除购物车
        fun onDeleteCartListResult(result: Boolean)
        //提交购物车
        fun onSubmitCartListResult(result: Int)
        //更新购物车
        fun onUpdateCartGoodsResult(result: Int)
    }

    interface Presenter : IBasePresenter<View> {

    }
}