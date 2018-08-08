package com.me.guanpj.mall.user.ui

import com.kotlin.user.data.protocol.UserInfo
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface LoginContract {
    interface View : IBaseView {
        fun onLoginResult(result: UserInfo)
    }

    interface Presenter : IBasePresenter<View> {

    }
}