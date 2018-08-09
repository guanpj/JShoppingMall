package com.me.guanpj.mall.goods.service

import com.me.guanpj.mall.goods.data.CartGoods
import rx.Observable

/*
    购物车 业务 接口
 */
interface CartService {
    /*
      添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    /*
      获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /*
      删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /*
      购物车结算
    */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>
}
