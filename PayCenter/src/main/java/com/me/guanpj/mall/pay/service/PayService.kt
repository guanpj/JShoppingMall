package com.kotlin.pay.service

import rx.Observable

/*
  支付业务接口
 */
interface PayService {

    /*
      获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    /*
      刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<Boolean>
}
