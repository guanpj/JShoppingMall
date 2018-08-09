package com.me.guanpj.mall.user.module.register

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface RegisterContract {
    interface View : IBaseView {
        fun onRegisterResult(result:String)
    }

    interface Presenter : IBasePresenter<View> {

    }
}