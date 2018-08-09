package com.me.guanpj.mall.goods.data

/*
  删除购物车商品请求
 */
data class DeleteCartReq(val cartIdList: List<Int> = arrayListOf())
