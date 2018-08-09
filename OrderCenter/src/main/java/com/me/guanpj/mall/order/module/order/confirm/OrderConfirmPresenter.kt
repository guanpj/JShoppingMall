package com.me.guanpj.mall.order.module.order.confirm

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.order.data.Order
import com.me.guanpj.mall.order.service.OrderService
import javax.inject.Inject

/*
  订单确认页 Presenter
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmContract.View>(), OrderConfirmContract.Presenter {

    @Inject
    lateinit var orderService: OrderService

    /*
      根据ID获取订单
     */
    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId).excute(object : BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, mLifecycleProvider)

    }

    /*
      提交订单
     */
    fun submitOrder(order: Order) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        }, mLifecycleProvider)
    }
}
