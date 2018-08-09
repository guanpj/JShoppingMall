package com.me.guanpj.mall.meassage.service

import com.me.guanpj.mall.meassage.data.Message
import rx.Observable

/*
  消息业务接口
 */
interface MessageService {
    //获取消息列表
    fun getMessageList(): Observable<MutableList<Message>?>
}
