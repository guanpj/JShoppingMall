package com.me.guanpj.mall.order.module.order.detail

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.order.data.Order
import com.me.guanpj.mall.order.module.order.confirm.OrderConfirmContract
import com.me.guanpj.mall.order.service.OrderService
import javax.inject.Inject

/*
  订单详情页Presenter
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailContract.View>(), OrderDetailContract.Presenter {

    @Inject
    lateinit var orderService: OrderService

    /*
      根据ID查询订单
     */
    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        orderService.getOrderById(orderId).excute(object : BaseSubscriber<Order>(getView()) {
            override fun onNext(t: Order) {
                getView().onGetOrderByIdResult(t)
            }
        }, mLifecycleProvider)
    }
}
