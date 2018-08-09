package com.me.guanpj.mall.goods.service.impl

import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.goods.data.repository.GoodsRepository
import com.me.guanpj.mall.goods.service.GoodsService
import com.me.guanpj.mall.library.ext.convert
import rx.Observable
import javax.inject.Inject

/*
  商品 业务层 实现类
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    /*
      获取商品列表
     */
    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

    /*
      根据关键字查询商品
     */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword, pageNo).convert()
    }

    /*
      获取商品详情
     */
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }
}
