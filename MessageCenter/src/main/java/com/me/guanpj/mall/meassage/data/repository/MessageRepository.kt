package com.me.guanpj.mall.meassage.data.repository


import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.library.data.net.RetrofitFactory
import com.me.guanpj.mall.meassage.data.Message
import com.me.guanpj.mall.meassage.data.api.MessageApi
import rx.Observable
import javax.inject.Inject


/*
  消息数据层
 */
class MessageRepository @Inject constructor() {

    /*
      获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }
}
