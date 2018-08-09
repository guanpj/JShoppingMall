package com.me.guanpj.mall.goods.data.repository

import com.me.guanpj.mall.goods.data.GetGoodsDetailReq
import com.me.guanpj.mall.goods.data.GetGoodsListByKeywordReq
import com.me.guanpj.mall.goods.data.GetGoodsListReq
import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.goods.data.api.GoodsApi
import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.library.data.net.RetrofitFactory
import rx.Observable
import javax.inject.Inject

/*
  商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
      根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    /*
      根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
    }

    /*
      商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsDetail(GetGoodsDetailReq(goodsId))
    }
}
