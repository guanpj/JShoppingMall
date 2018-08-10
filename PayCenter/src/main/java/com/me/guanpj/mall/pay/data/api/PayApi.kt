package com.me.guanpj.mall.pay.data.api

import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.pay.data.GetPaySignReq
import com.me.guanpj.mall.pay.data.PayOrderReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


/*
  支付接口
 */
interface PayApi {

    /*
      获取支付宝支付签名
     */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /*
      刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>
}
