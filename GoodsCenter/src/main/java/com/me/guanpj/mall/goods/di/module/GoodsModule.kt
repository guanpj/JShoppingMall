package com.me.guanpj.mall.goods.di.module

import com.me.guanpj.mall.goods.service.GoodsService
import com.me.guanpj.mall.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/*
  商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodservice(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }
}
