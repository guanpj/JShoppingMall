package com.me.guanpj.mall.meassage.data.api

import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.meassage.data.Message
import retrofit2.http.POST
import rx.Observable

/*
  消息接口
 */
interface MessageApi {

    /*
      获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
