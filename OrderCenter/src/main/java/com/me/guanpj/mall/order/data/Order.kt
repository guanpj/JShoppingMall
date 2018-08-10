package com.me.guanpj.mall.order.data

/*
  订单数据类
 */
data class Order(
        val id: Int,
        val payType: Int,
        var shipAddress: Address?,
        val totalPrice: Long,
        var orderStatus: Int,
        val orderGoodsList: MutableList<OrderGoods>
)

