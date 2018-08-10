package com.me.guanpj.mall.order.di.module

import com.me.guanpj.mall.order.service.AddressService
import com.me.guanpj.mall.order.service.impl.AddressServiceImpl
import dagger.Module
import dagger.Provides

/*
  收货人信息Module
 */
@Module
class AddressModule {

    @Provides
    fun provideShipAddressservice(addressService: AddressServiceImpl): AddressService {
        return addressService
    }
}
