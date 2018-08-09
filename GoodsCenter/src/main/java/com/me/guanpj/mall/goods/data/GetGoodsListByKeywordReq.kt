package com.me.guanpj.mall.goods.data

/*
  按关键字搜索商品
 */
data class GetGoodsListByKeywordReq(
        val keyword: String,
        val pageNo: Int
)
