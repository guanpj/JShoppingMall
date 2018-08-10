package com.me.guanpj.mall.meassage.di.module

import com.me.guanpj.mall.meassage.service.MessageService
import com.me.guanpj.mall.meassage.service.impl.MessageServiceImpl
import dagger.Module
import dagger.Provides

/*
  消息模块业务注入
 */
@Module
class MessageModule {

    @Provides
    fun provideMessageService(messageService: MessageServiceImpl): MessageService {
        return  messageService
    }
}
