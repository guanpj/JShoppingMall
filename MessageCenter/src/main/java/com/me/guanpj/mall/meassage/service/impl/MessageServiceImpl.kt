package com.me.guanpj.mall.meassage.service.impl


import com.me.guanpj.mall.library.ext.convert
import com.me.guanpj.mall.meassage.data.Message
import com.me.guanpj.mall.meassage.data.repository.MessageRepository
import com.me.guanpj.mall.meassage.service.MessageService
import rx.Observable
import javax.inject.Inject

/*
  消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
      获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }
}
