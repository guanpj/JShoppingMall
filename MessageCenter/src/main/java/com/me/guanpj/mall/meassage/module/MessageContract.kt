package com.me.guanpj.mall.meassage.module

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.meassage.data.Message

interface MessageContract {
    interface View : IBaseView {
        //获取消息列表回调
        fun onGetMessageResult(result:MutableList<Message>?)
    }

    interface Presenter : IBasePresenter<View>
}