package com.me.guanpj.mall.pay.data.repository

import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.library.data.net.RetrofitFactory
import com.me.guanpj.mall.pay.data.GetPaySignReq
import com.me.guanpj.mall.pay.data.PayOrderReq
import com.me.guanpj.mall.pay.data.api.PayApi
import rx.Observable
import javax.inject.Inject


/*
  支付数据层
 */
class PayRepository @Inject constructor() {

    /*
      获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
      刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))
    }
}
