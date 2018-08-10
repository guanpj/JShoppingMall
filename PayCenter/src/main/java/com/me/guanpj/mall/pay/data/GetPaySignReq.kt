package com.me.guanpj.mall.pay.data

/*
  获取支付宝 支付签名
 */
data class GetPaySignReq(val orderId: Int, val totalPrice: Long)
