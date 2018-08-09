package com.me.guanpj.mall.goods.data

/*
  提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>,val totalPrice: Long)
