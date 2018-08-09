package com.me.guanpj.mall.order.module.order.list

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.order.data.Order

interface OrderListContract {
    interface View : IBaseView {
        //获取订单列表回调
        fun onGetOrderListResult(result:MutableList<Order>?)
        //确认订单回调
        fun onConfirmOrderResult(result:Boolean)
        //取消订单回调
        fun onCancelOrderResult(result:Boolean)
    }

    interface Presenter : IBasePresenter<View>
}