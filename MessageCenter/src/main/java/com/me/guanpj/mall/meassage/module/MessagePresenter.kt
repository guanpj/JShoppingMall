package com.me.guanpj.mall.meassage.module

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.meassage.data.Message
import com.me.guanpj.mall.meassage.service.MessageService
import javax.inject.Inject

/*
  消息列表 Presenter
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageContract.View>(), MessageContract.Presenter {

    @Inject
    lateinit var messageService: MessageService

    /*
      获取消息列表
     */
    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        messageService.getMessageList().excute(object : BaseSubscriber<MutableList<Message>?>(getView()) {
            override fun onNext(t: MutableList<Message>?) {
                getView().onGetMessageResult(t)
            }
        }, mLifecycleProvider)
    }
}
