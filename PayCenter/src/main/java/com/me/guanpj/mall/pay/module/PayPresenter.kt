package com.me.guanpj.mall.pay.module

import com.kotlin.pay.service.PayService
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import javax.inject.Inject

/*
  支付Presenter
 */
class PayPresenter @Inject constructor() : BasePresenter<PayContract.View>(), PayContract.Presenter {

    @Inject
    lateinit var service: PayService

    /*
      获取支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        service.getPaySign(orderId, totalPrice).excute(object : BaseSubscriber<String>(getView()) {
            override fun onNext(t: String) {
                getView().onGetSignResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      订单支付，同步订单状态
     */
    fun payOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        service.payOrder(orderId).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                getView().onPayOrderResult(t)
            }
        }, mLifecycleProvider)
    }
}
