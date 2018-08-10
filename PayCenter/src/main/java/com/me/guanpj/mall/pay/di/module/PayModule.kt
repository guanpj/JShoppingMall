package com.me.guanpj.mall.pay.di.module

import com.kotlin.pay.service.PayService
import com.kotlin.pay.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/*
  支付 Module
 */
@Module
class PayModule {

    @Provides
    fun providePayService(payService: PayServiceImpl): PayService {
        return payService
    }
}
