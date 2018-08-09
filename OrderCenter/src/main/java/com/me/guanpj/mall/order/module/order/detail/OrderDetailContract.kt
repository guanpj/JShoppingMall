package com.me.guanpj.mall.order.module.order.detail

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.order.data.Order

interface OrderDetailContract {
    interface View : IBaseView {
        //获取订单回调
        fun onGetOrderByIdResult(result: Order)
    }

    interface Presenter : IBasePresenter<View>
}