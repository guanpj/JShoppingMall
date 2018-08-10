package com.me.guanpj.mall.goods.di.module

import com.me.guanpj.mall.goods.service.CartService
import com.me.guanpj.mall.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/*
  购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }
}
