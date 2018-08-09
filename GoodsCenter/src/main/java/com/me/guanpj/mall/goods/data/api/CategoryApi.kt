package com.me.guanpj.mall.goods.data.api

import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.goods.data.GetCategoryReq
import com.me.guanpj.mall.library.data.BaseResp
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
  商品分类接口
 */
interface CategoryApi {
    /*
      获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}
