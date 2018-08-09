package com.me.guanpj.mall.goods.data.api

import com.me.guanpj.mall.goods.data.GetGoodsDetailReq
import com.me.guanpj.mall.goods.data.GetGoodsListByKeywordReq
import com.me.guanpj.mall.goods.data.GetGoodsListReq
import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.library.data.BaseResp
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
  商品接口
 */
interface GoodsApi {
    /*
      获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
      获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
      获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
