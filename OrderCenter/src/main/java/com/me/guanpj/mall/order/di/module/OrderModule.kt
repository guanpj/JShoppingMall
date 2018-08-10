package com.me.guanpj.mall.order.di.module

import com.me.guanpj.mall.order.service.OrderService
import com.me.guanpj.mall.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/*
  订单Module
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderservice(orderService: OrderServiceImpl): OrderService {
        return orderService
    }
}
