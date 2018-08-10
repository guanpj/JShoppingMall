package com.kotlin.user.injection.component

import com.kotlin.user.injection.module.UploadModule
import com.kotlin.user.injection.module.UserModule
import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import com.me.guanpj.mall.user.module.forgetpwd.ForgetPwdActivity
import com.me.guanpj.mall.user.module.login.LoginActivity
import com.me.guanpj.mall.user.module.register.RegisterActivity
import com.me.guanpj.mall.user.module.resetpwd.ResetPwdActivity
import com.me.guanpj.mall.user.module.user.UserInfoActivity
import dagger.Component

/*
  用户模块Component
 */
@ComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class, UploadModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}
