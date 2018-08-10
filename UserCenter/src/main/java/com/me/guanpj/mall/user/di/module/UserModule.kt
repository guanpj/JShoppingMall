package com.kotlin.user.injection.module

import com.me.guanpj.mall.user.service.UserService
import com.me.guanpj.mall.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/*
  用户模块Module
 */
@Module
class UserModule {

    @Provides
    fun provideUserService(userService: UserServiceImpl): UserService {
        return userService
    }
}

