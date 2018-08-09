package com.me.guanpj.mall.di.module

import com.me.guanpj.mall.di.component.BaseActivityComponent
import com.me.guanpj.mall.goods.module.goods.list.GoodsListActivity
import com.me.guanpj.mall.goods.module.goods.list.GoodsListActivityModule
import com.me.guanpj.mall.order.module.address.edit.AddressEditActivity
import com.me.guanpj.mall.order.module.address.edit.AddressEditActivityModule
import com.me.guanpj.mall.order.module.address.list.AddressListActivity
import com.me.guanpj.mall.order.module.address.list.AddressListActivityModule
import com.me.guanpj.mall.order.module.order.confirm.OrderConfirmActivity
import com.me.guanpj.mall.order.module.order.confirm.OrderConfirmActivityModule
import com.me.guanpj.mall.order.module.order.detail.OrderDetailActivity
import com.me.guanpj.mall.order.module.order.detail.OrderDetailActivityModule
import com.me.guanpj.mall.user.module.forgetpwd.ForgetPwdActivity
import com.me.guanpj.mall.user.module.forgetpwd.ForgetPwdActivityModule
import com.me.guanpj.mall.user.module.login.LoginActivity
import com.me.guanpj.mall.user.module.login.LoginActivityModule
import com.me.guanpj.mall.user.module.register.RegisterActivity
import com.me.guanpj.mall.user.module.register.RegisterActivityModule
import com.me.guanpj.mall.user.module.resetpwd.ResetPwdActivity
import com.me.guanpj.mall.user.module.resetpwd.ResetPwdActivityModule
import com.me.guanpj.mall.user.module.user.UserInfoActivity
import com.me.guanpj.mall.user.module.user.UserInfoActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = arrayOf(BaseActivityComponent::class))
abstract class AllActivityModule {

    @ContributesAndroidInjector(modules = [GoodsListActivityModule::class])
    abstract fun contributesGoodsListActivityInjector(): GoodsListActivity


    @ContributesAndroidInjector(modules = [AddressEditActivityModule::class])
    abstract fun contributesAddressEditActivityInjector(): AddressEditActivity

    @ContributesAndroidInjector(modules = [AddressListActivityModule::class])
    abstract fun contributesAddressListActivityInjector(): AddressListActivity

    @ContributesAndroidInjector(modules = [OrderConfirmActivityModule::class])
    abstract fun contributesOrderConfirmActivityInjector(): OrderConfirmActivity

    @ContributesAndroidInjector(modules = [OrderDetailActivityModule::class])
    abstract fun contributesOrderDetailActivityInjector(): OrderDetailActivity


    @ContributesAndroidInjector(modules = [ForgetPwdActivityModule::class])
    abstract fun contributesForgetPwdActivityInjector(): ForgetPwdActivity

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributesLoginActivityInjector(): LoginActivity

    @ContributesAndroidInjector(modules = [RegisterActivityModule::class])
    abstract fun contributesRegisterActivityInjector(): RegisterActivity

    @ContributesAndroidInjector(modules = [ResetPwdActivityModule::class])
    abstract fun contributesResetPwdActivityInjector(): ResetPwdActivity

    @ContributesAndroidInjector(modules = [UserInfoActivityModule::class])
    abstract fun contributesUserInfoActivityInjector(): UserInfoActivity
}