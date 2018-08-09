package com.me.guanpj.mall.order.module.order.confirm

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.order.data.Order

interface OrderConfirmContract {
    interface View : IBaseView {
        //获取订单回调
        fun onGetOrderByIdResult(result: Order)
        //提交订单回调
        fun onSubmitOrderResult(result:Boolean)
    }

    interface Presenter : IBasePresenter<View>
}