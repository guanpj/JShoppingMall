package com.me.guanpj.mall.order.di.component

import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import com.me.guanpj.mall.order.di.module.AddressModule
import com.me.guanpj.mall.order.module.address.edit.AddressEditActivity
import com.me.guanpj.mall.order.module.address.list.AddressListActivity
import dagger.Component

/*
  收货人信息Component
 */
@ComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(AddressModule::class))
interface AddressComponent {
    fun inject(activity: AddressEditActivity)
    fun inject(activity: AddressListActivity)
}
