package com.me.guanpj.mall.goods.data.api

import com.me.guanpj.mall.goods.data.*
import com.me.guanpj.mall.library.data.BaseResp
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
  购物车接口
 */
interface CartApi {
    /*
      获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>>

    /*
      添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResp<Int>>

    /*
      删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResp<String>>

    /*
      提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>

    /*
      更新购物车的数据
     */
    @POST("cart/updateCartGoods")
    fun updateCartGoods(@Body req: UpdateGoodsListReq): Observable<BaseResp<Int>>
}
